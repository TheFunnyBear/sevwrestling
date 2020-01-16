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
                <h6 class="mb-0">Создание фотоальбома</h6>
              </template>
                <!--Название альбома-->
                <b-form-group
                        id="input-group-1"
                        label="Название альбома:"
                        label-for="input-1"
                        description="Указнный тут заголовок новости будет отображаться в списке новостей и при детальном просмотре новости."
                >
                  <b-form-input
                          id="input-1"
                          v-model="form.photoAlbumTitle"
                          type="text"
                          required
                          placeholder="Укажите тут название альбома"
                  ></b-form-input>
                </b-form-group>

                <!--Описание новости-->
                <b-form-group id="input-group-2" label="Описание альбома:" label-for="input-2">
                  <b-form-textarea
                          id="input-2"
                          v-model="form.photoAlbumDescription"
                          rows="5"
                          max-rows="6"
                          placeholder="Укажите тут описание для альбома"
                  ></b-form-textarea>
                </b-form-group>

              <template v-slot:footer>
                <b-row align-v="center">
                  <b-col align="right">
                    <b-button type="submit" variant="primary">Обновить фотоальбом</b-button>
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
  import moment from "moment";

  export default {
    components: {NavigationHeader, PageHeader, PageFooter},
    data() {
      return {
        form: {
          photoAlbumTitle: '',
          photoAlbumDescription: ''
        },
        PhotoAlbumModel : {
          /**
           * Идентификатор фотоальбома
           */
          id: 0,

          /**
           * Заголовок фотоальбома
           */
          photoAlbumTitle: '',

          /**
           * Описание фотоальбома
           */
          photoAlbumDescription: ''
        },
        oldPhotoAlbumModel: null,
        isAdminMode: true,
        pageTitle: 'Редактирование альбома для фотографий',
        pageDescription: 'На этой странице вы можите отредактировать альбом фотографий.',
        show: true,
      }
    },
    created: function () {
      console.log("Create function invoked!");
      let id = this.$route.params.id;
      let url = `photoAlbum/${id}`;
      this.$http.get(url).then(result =>
              result.json().then(data => {
                        this.oldPhotoAlbumModel = data;
                        this.form.photoAlbumTitle = this.oldPhotoAlbumModel.photoAlbumTitle;
                        this.form.photoAlbumDescription = this.oldPhotoAlbumModel.photoAlbumDescription;
                      }
              )
      )
    },
    methods: {
      onSubmit(evt) {
        console.log("OnSubmit function invoked!");
        evt.preventDefault();

        this.PhotoAlbumModel.photoAlbumTitle = this.form.photoAlbumTitle;
        this.PhotoAlbumModel.photoAlbumDescription = this.form.photoAlbumDescription;

        let id = this.$route.params.id;
        let url = `photoAlbum/update/${id}`;
        this.$http.post(url, JSON.stringify( this.PhotoAlbumModel)).then(response => {
          console.log("Post response completed with status:", response.status);
          this.$router.push('/photoAlbums_manage');
        }, response => {
          // error callback
        });
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
      }
    }

  }
</script>
