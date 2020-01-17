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
                <h6 class="mb-0">Создание новости</h6>
              </template>

              <!--Дата новости-->
              <b-form-group
                      id="input-group-1"
                      label="Дата новости:"
                      label-for="input-1"
                      description="Указнная тут дата новости будет отображаться в списке новостей и при детальном просмотре новости."
              >
                <b-form-input
                        id="input-1"
                        v-model="form.publicationDate"
                        type="date"
                        required
                ></b-form-input>
              </b-form-group>

              <!--Заголовок новости-->
              <b-form-group
                      id="input-group-2"
                      label="Зоголовок новости:"
                      label-for="input-2"
                      description="Указнный тут заголовок новости будет отображаться в списке новостей и при детальном просмотре новости."
              >
                <b-form-input
                        id="input-2"
                        v-model="form.title"
                        type="text"
                        required
                        placeholder="Укажите тут заголовок новости"
                ></b-form-input>
              </b-form-group>

              <!--Описание новости-->
              <b-form-group id="input-group-3" label="Описание новости:" label-for="input-3">
                <b-form-textarea
                        id="input-3"
                        v-model="form.description"
                        rows="5"
                        max-rows="6"
                        required
                        placeholder="Укажите тут описание новости"
                ></b-form-textarea>
              </b-form-group>

              <!--Файл с фотографией-->
              <b-form-group id="input-group-4" label="Фотография для новости:" label-for="input-4">
                <b-form-file
                      id="input-4"
                      v-model="form.photoFile"
                      :state="Boolean(form.photoFile)"
                      accept="image/jpeg, image/png, image/gif"
                      placeholder="Выберите файл или перетащите его сюда..."
                      drop-placeholder="Перетащите сюда файл..."
              ></b-form-file>
                <div v-if="form.photoFile">
                <div class="mt-3">Выбран файл с фотографией: {{ form.photoFile ? form.photoFile.name : '' }}</div>
              </div>
                <div v-else>
                  <div class="mt-3">Файл с фотографией не выбран.</div>
                </div>
              </b-form-group>
              <template v-slot:footer>
                <b-row align-v="center">
                  <b-col align="right">
                    <b-button type="submit" variant="primary">Добавить новость</b-button>
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
  import moment from 'moment';
  import NavigationHeader from '../../components/NavigationHeader.vue';
  import PageHeader from '../../components/PageHeader.vue';
  import PageFooter from '../../components/PageFooter.vue';

  export default {
    components: {NavigationHeader, PageHeader, PageFooter},
    created: function () {
      console.log("Create function invoked!");

      /*
      let currentDate = moment.utc(moment().format()).format("DD.MM.YYYY");
      console.info("Current date is:", currentDate);
      this.form.publicationDate = currentDate;
      */
    },
    data() {
      return {
        form: {
          publicationDate: '',
          title: '',
          description: '',
          photoFile: null,
        },
        MessageModel: {
          /**
           * Идентификатор записи
           */
          id: '',

          /**
           * Дата создания сообщения
           */
          createdDate: null,

          /**
           * Дата публикации сообщения
           */
          publicationDate: null,

          /**
           * Заголовок сообщения
           */
          messageTitle: '',

          /**
           * Текст сообщения
           */
          messageText: '',

          /**
           * Идентификатор изображения
           */
          pictureUuid: null,

          /**
           *  Уникальное имя файла
           */
          uniqFileName: '',

          /**
           * Сообщение удалено
           */
          isDeleted: false
        },
        fileUuid: '',
        uploadFileUniqName: '',
        isAdminMode: true,
        pageTitle: 'Добавить новость',
        pageDescription: 'На этой странице вы можите добавить новость.',
        show: true
      }
    },
    methods: {
      onSubmit(evt) {
        console.log("OnSubmit function invoked!");
        evt.preventDefault();
        this.uploadFile();
        console.log("OnSubmit function completed.");
      },

      onReset(evt) {
        console.log("OnReset function invoked!");
        evt.preventDefault();
        // Reset our form values
        this.form.title = '';
        this.form.description = '';
        this.form.photoFile = null;
        this.form.checked = [];
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

        this.MessageModel.createdDate = moment.utc(this.publicationDate);
        this.MessageModel.publicationDate = moment.utc(moment().format());
        this.MessageModel.messageTitle = this.form.title;
        this.MessageModel.messageText = this.form.description;
        this.MessageModel.pictureUuid = this.fileUuid;
        this.MessageModel.uniqFileName = this.uploadFileUniqName;
        this.MessageModel.isDeleted = false;

        let url = `message/create/`;
        this.$http.post(url, JSON.stringify( this.MessageModel)).then(response => {
          console.log("Post response completed with status:", response.status);
          this.$router.push('/news_list');

        }, response => {
          // error callback
        });
      },
    }
  }
</script>

<style>
  .custom-file-input:lang(en) ~ .custom-file-label::after {
    content: 'Выбрать файл';
  }
</style>
