<template>
    <div>
        <NavigationHeader :isAdminMode="isAdminMode"></NavigationHeader>
        <PageHeader :pageTitle="pageTitle" :pageDescription="pageDescription"/>

        <b-container fluid>
            <b-row align-v="center">
                <b-col align="left" cols="8" align-self="center">
                    <b-form @submit="onSubmit" @reset="onReset" v-if="show">
                        <b-card header-tag="header" footer-tag="footer">
                            <template v-slot:header>
                                <h6 class="mb-0">Добавление видео файла</h6>
                            </template>

                            <!-- Название альбома -->
                            <b-form-group
                                    id="input-group-1"
                                    label="Фото альбом:"
                                    label-for="input-1"
                                    description="Выбирите фотоальбом в который будет добавленно фото."
                            >
                                <b-form-select
                                        v-model="form.selectedAlbum"
                                        id="input-1"
                                        :options="options"
                                        class="mb-3">

                                    <!-- This slot appears above the options from 'options' prop -->
                                    <template v-slot:first>
                                        <option :value="null" disabled>-- Пожалуйста, выбирите название фото альбома --</option>
                                    </template>

                                </b-form-select>
                                <CommentForControl
                                        :showFirstMessage=Boolean(form.selectedAlbum)
                                        :firstMessage="'Выбран фотоальбом:'"
                                        :firstMessagePostfix=form.selectedAlbum
                                        :secondMessage = "'Фотоальбом не выбран.'">
                                </CommentForControl>
                            </b-form-group>

                            <!--Коментарий к фотографии-->
                            <b-form-group
                                    id="input-group-2"
                                    label="Коментарий к фотографии:"
                                    label-for="input-2"
                                    description="Указнный тут коментарий будет отабражаться рядом с фотографией."
                            >
                                <b-form-input
                                        id="input-2"
                                        v-model="form.comment"
                                        type="text"
                                        required
                                        placeholder="Укажите тут коментарий к фотографии"
                                ></b-form-input>
                            </b-form-group>

                            <div v-if="show && oldPicture != null && oldPicture.pictureDownloadUri != null">
                                <div>Текущая фотография:</div>
                                <img :src="oldPicture.pictureDownloadUri" :alt="oldPicture.comment">
                            </div>
                            <div v-else>
                                <i>Предыдущее изображение ещё не загруженно.</i>
                            </div>

                            <!--Файл с фотографией-->
                            <b-form-group id="input-group-3" label="Новая фотография (если нужно заменить текущую):" label-for="input-3">
                                <b-form-file
                                        id="input-3"
                                        v-model="form.photoFile"
                                        accept="image/jpeg, image/png, image/gif"
                                        placeholder="Выберите файл или перетащите его сюда..."
                                        drop-placeholder="Перетащите сюда файл..."
                                ></b-form-file>
                                <CommentForControl
                                        :showFirstMessage=Boolean(form.photoFile)
                                        :firstMessage="'Выбран файл с фотографией:'"
                                        :firstMessagePostfix=getPhotoFileName()
                                        :secondMessage = "'Файл с фотографией не выбран.'">
                                </CommentForControl>
                            </b-form-group>
                            <template v-slot:footer>
                                <b-row align-v="center">
                                    <b-col align="right">
                                        <b-button type="submit" variant="primary">Обновить фотографию</b-button>
                                        <b-button type="reset" variant="danger">Очистить</b-button>
                                    </b-col>
                                </b-row>
                            </template>
                        </b-card>
                    </b-form>
                </b-col>
            </b-row>
        </b-container>
        <PageFooter/>
    </div>
</template>

<script>
    import NavigationHeader from '../../components/NavigationHeader.vue';
    import PageHeader from '../../components/PageHeader.vue';
    import PageFooter from '../../components/PageFooter.vue';
    import CommentForControl from "../../components/CommentForControl.vue";
    import moment from "moment";


    export default {
        name: "photos-edit-page",
        components: {NavigationHeader, PageHeader, PageFooter, CommentForControl},
        data() {
            return {
                form: {
                    comment: '',
                    photoFile: null,
                    selectedAlbum: null,
                },
                PictureModel: {
                    /**
                     * Идентификатор изображения
                     */
                    id: 0,

                    /**
                     * Уникальны идентификатор изображения
                     */
                    pictureUuid: null,

                    /**
                     * Идентификатор фотоальбома
                     */
                    photoAlbumId: 0,

                    /**
                     *  Уникальное имя файла
                     */
                    uniqFileName: '',

                    /**
                     * Коментарий к файлу
                     */
                    fileComment: '',

                    /**
                     * Файл удалён
                     */
                    isDeleted: false
                },
                fileUuid: '',
                uploadFileUniqName: '',
                size: 0,
                isAdminMode: true,
                pageTitle: 'Редактировать фотографию в альбоме',
                pageDescription: 'На этой странице вы можите отредактировать фотографию в альбом.',
                show: true,
                options: [],
                pictureUrl: null,
                oldPicture: null
            }
        },
        created: function () {
            console.log("Create function invoked!");
            let url = `photoAlbums/`;
            this.$http.get(url).then(result =>
                result.json().then(data => {
                        data.forEach(photoAlbumModel => this.options.push(
                            {
                                value: photoAlbumModel.id,
                                text: photoAlbumModel.photoAlbumTitle + " | " + photoAlbumModel.photoAlbumDescription
                            })
                        );
                    }
                )
            );

            let id = this.$route.params.id;
            let pictureUrl = `picture/${id}/`;
            this.$http.get(pictureUrl).then(result =>
                result.json().then(data => {
                        this.oldPicture = data;
                        this.form.comment = data.fileComment;
                        this.form.selectedAlbum = data.photoAlbumId;

                        this.show = false;
                        this.$nextTick(() => {
                            this.show = true;
                        });
                    }
                )
            )
        },

        methods: {
            onSubmit(evt) {
                console.log("OnSubmit function invoked!");
                evt.preventDefault();

                if (this.form.photoFile != null) {
                    //Оюновляем файл если выбрали новый
                    this.uploadFile();
                }
                else {
                    this.sendData();
                }


                console.log("OnSubmit function completed.");
            },

            onReset(evt) {
                evt.preventDefault();
                // Reset our form values

                this.form.comment = this.oldPicture.fileComment;
                this.form.selectedAlbum = this.oldPicture.photoAlbumId;
                this.form.photoFile = null;

                // Trick to reset/clear native browser form validation state
                this.show = false;
                this.$nextTick(() => {
                    this.show = true
                })
            },

            uploadFile: function () {
                console.log('Sending file');
                let formData = new FormData();
                formData.append('file', this.form.photoFile);

                let url = `files/uploadFile/`;
                this.$http.post(
                    url,
                    formData,
                    {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    }
                ).then(result =>
                    result.json().then(data => {
                            this.fileUuid = data.fileUuid;
                            this.uploadFileUniqName = data.uniqFileName;
                            this.size = data.size;
                            console.log("File uploaded start sending data");
                            this.sendData();
                        }
                    ), response => {
                    console.log("File was not uploaded not sending data");
                    // error callback
                });
            },

            sendData: function () {
                console.log('Sending data');

                if (this.form.photoFile != null) {
                    this.PictureModel.pictureUuid = this.fileUuid;
                    this.PictureModel.uniqFileName = this.uploadFileUniqName;
                }
                else {
                    this.PictureModel.pictureUuid = this.oldPicture.pictureUuid;
                    this.PictureModel.uniqFileName = this.oldPicture.uniqFileName;
                }

                this.PictureModel.photoAlbumId = this.form.selectedAlbum;
                this.PictureModel.fileComment = this.form.comment;
                this.PictureModel.isDeleted = false;

                let id = this.$route.params.id;
                let url = `pictures/update/${id}/`;
                this.$http.post(url, JSON.stringify(this.PictureModel)).then(response => {
                    console.log("Post response completed with status:", response.status);
                    this.$router.push('/photos_manage');
                }, response => {
                    // error callback
                });
            },

            getPhotoFileName: function() {
                if (this.form.photoFile != null)
                {
                    return this.form.photoFile.name;
                }
                return '';
            }
        }
    }
</script>

<style scoped>

</style>