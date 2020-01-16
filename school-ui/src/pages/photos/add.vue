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

              <!--Файл с фотографией-->
              <b-form-group id="input-group-3" label="Фотография для новости:" label-for="input-3">
                <b-form-file
                        id="input-3"
                        v-model="form.photoFile"
                        :state="Boolean(form.photoFile)"
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
                  <b-button type="submit" variant="primary">Добавить фотографию</b-button>
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
    components: {NavigationHeader, PageHeader, PageFooter, CommentForControl},
    data() {
      return {
        form: {
          comment: '',
          photoFile: null,
          selectedAlbum: null,
        },
        PictureModel : {
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
        pageTitle: 'Добавить фотографию в альбом',
        pageDescription: 'На этой странице вы можите добавить фотографию в альбом.',
        show: true,
        options: []
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
                                  text: photoAlbumModel.photoAlbumTitle + " | " +photoAlbumModel.photoAlbumDescription
                                }));
                      }
              )
      )
    },

    methods: {
      onSubmit(evt) {
        console.log("OnSubmit function invoked!");
        evt.preventDefault();
        this.uploadFile();
        console.log("OnSubmit function completed.");
      },

      onReset(evt) {
        evt.preventDefault();
        // Reset our form values
        this.form.comment = '';
        this.form.photoFile = null;
        this.form.selectedAlbum = null;
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

        this.PictureModel.pictureUuid = this.fileUuid;
        this.PictureModel.photoAlbumId = this.form.selectedAlbum;
        this.PictureModel.uniqFileName = this.uploadFileUniqName;
        this.PictureModel.fileComment = this.form.comment;
        this.PictureModel.isDeleted = false;

        let url = `pictures/create/`;
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
