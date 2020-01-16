<template>
  <div>
    <NavigationHeader :isAdminMode="isAdminMode"></NavigationHeader>
    <PageHeader :pageTitle="pageTitle" :pageDescription="pageDescription"/>

    <b-container fluid>
      <b-form @submit="onSubmit" @reset="onReset" v-if="show">
      <b-row align-v="center">
        <b-col align="left" cols="8" align-self="center">
          <b-card header-tag="header" footer-tag="footer">
            <template v-slot:header>
              <h6 class="mb-0">Добавление видео файла</h6>
            </template>
              <!--Название видео-->
              <b-form-group
                      id="input-group-1"
                      label="Название видео:"
                      label-for="input-1"
                      description="Указнный тут заголовок будет отабражаться рядом с видео."
              >
                <b-form-input
                        id="input-1"
                        v-model="form.title"
                        type="text"
                        required
                        placeholder="Укажите тут заголовок новости"
                ></b-form-input>
              </b-form-group>

              <!--Файл с видео-->
              <b-form-group id="input-group-2" label="Видео файл:" label-for="input-2">
                <b-form-file
                        id="input-2"
                        v-model="form.videoFile"
                        :state="Boolean(form.videoFile)"
                        accept="video/mpg, video/avi, video/mov"
                        placeholder="Выберите файл или перетащите его сюда..."
                        drop-placeholder="Перетащите сюда файл..."
                ></b-form-file>
                <div v-if="form.videoFile">
                  <div class="mt-3">Выбран файл с видео: {{ form.photoFile ? form.photoFile.name : '' }}</div>
                </div>
                <div v-else>
                  <div class="mt-3">Файл с видео не выбран.</div>
                </div>
              </b-form-group>

            <template v-slot:footer>
              <b-row align-v="center">
                <b-col align="right">
                  <b-button type="submit" variant="primary">Добавить видео</b-button>
                  <b-button type="reset" variant="danger">Очистить</b-button>
                </b-col>
              </b-row>
            </template>
          </b-card>
        </b-col>
      </b-row>
      </b-form>
    </b-container>

    <PageFooter/>
  </div>
</template>

<script>
  import NavigationHeader from '../../components/NavigationHeader.vue';
  import PageHeader from '../../components/PageHeader.vue';
  import PageFooter from '../../components/PageFooter.vue';

  export default {
    components: {NavigationHeader, PageHeader, PageFooter},
    data() {
      return {
        form: {
          title: '',
          videoFile: null,
        },
        VideoModel : {
          /**
           * Идентификатор изображения
           */
          id: 0,

          /**
           * Уникальны идентификатор видео
           */
          videoUuid: null,

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
        isAdminMode: true,
        pageTitle: 'Добавить видео',
        pageDescription: 'На этой странице вы можите добавить видео на сайт.',
        show: true
      }
    },
    methods: {
      onSubmit(evt) {
        console.log("OnSubmit function invoked!");
        evt.preventDefault();

        this.uploadVideoFile();
        console.log("OnSubmit function completed.");
      },

      onReset(evt) {
        evt.preventDefault();
        // Reset our form values
        this.form.title = '';
        this.form.videoFile = null;
        // Trick to reset/clear native browser form validation state
        this.show = false;
        this.$nextTick(() => {
          this.show = true
        })
      },

    uploadVideoFile: function () {
      console.log('Sending file');
      let formData = new FormData();
      formData.append('file', this.form.videoFile);

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

      this.VideoModel.videoUuid = this.fileUuid;
      this.VideoModel.uniqFileName = this.uploadFileUniqName;
      this.VideoModel.fileComment = this.form.title;
      this.VideoModel.isDeleted = false;

      let url = `videos/create/`;
      this.$http.post(url, JSON.stringify(this.VideoModel)).then(response => {
        console.log("Post response completed with status:", response.status);
        this.$router.push('/video_list');
      }, response => {
        // error callback
      });
    }

    }
  }
</script>
