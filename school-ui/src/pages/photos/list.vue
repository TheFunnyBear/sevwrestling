<template>
  <div>
    <NavigationHeader :isAdminMode="isAdminMode"></NavigationHeader>
    <PageHeader :pageTitle="pageTitle" :pageDescription="pageDescription"/>

    <b-form v-if="show">
      <!-- Название альбома -->
      <b-form-group
              id="input-group-1"
              label="Фото альбом:"
              label-for="input-1"
              description="Выбирите фотоальбом из которого будут отображаться фото."
      >
        <b-form-select
                v-model="selectedAlbum"
                id="input-1"
                :options="options"
                class="mb-3"
                v-on:change="onPhotoAlbumChange">

          <!-- This slot appears above the options from 'options' prop -->
          <template v-slot:first>
            <option :value="null" disabled>-- Пожалуйста, выбирите название фото альбома --</option>
          </template>

        </b-form-select>
        <CommentForControl
                :showFirstMessage=Boolean(selectedAlbum)
                :firstMessage="'Выбран фотоальбом:'"
                :firstMessagePostfix=selectedAlbum
                :secondMessage = "'Фотоальбом не выбран.'">
        </CommentForControl>
      </b-form-group>
    </b-form>

    <div v-if="show">
      <b-container fluid class="p-4 bg-dark" v-if="pictures != null && pictures.length > 0">
        <b-row>
          <b-col>
            <PictureItem v-for="picture in pictures" v-bind:key="picture.id" :picture="picture"/>
          </b-col>
        </b-row>
      </b-container>
    </div>
    <PageFooter/>
  </div>
</template>

<script>
  import NavigationHeader from '../../components/NavigationHeader.vue';
  import PageHeader from '../../components/PageHeader.vue';
  import PageFooter from '../../components/PageFooter.vue';
  import CommentForControl from "../../components/CommentForControl.vue";
  import PictureItem from "../../components/picture/PictureItem.vue";

  export default {
    components: {NavigationHeader, PageHeader, PageFooter, CommentForControl, PictureItem},

    data() {
      return {
        isAdminMode: true,
        pageTitle: 'Просмотр фотографий',
        pageDescription: 'На этой странице просмотреть фотоальбомы с фотогафиями.',
        selectedAlbum: null,
        show: true,
        options: [],
        pictures: [],
        mainProps: {
          center: true,
          fluidGrow: true,
          blank: true,
          blankColor: '#bbb',
          width: 600,
          height: 400,
          class: 'my-5'
        }
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
                                  text: photoAlbumModel.photoAlbumDescription
                                }));

                        if(this.$route != null && this.$route.params != null && this.$route.params.id != null) {
                          console.info("Route specified");
                          let id = this.$route.params.id;
                          if (id > 0) {
                            this.selectedAlbum = id;
                          }
                        }
                        else {
                          console.info("Route not specified. Try to select first album.");
                          if (this.options.length > 0) {
                            console.info("Route not specified. Albums count is:", this.options.length);
                            this.selectedAlbum = this.options[0].value;
                          }
                        }
                        this.loadPicturesInSelectedAlbum();
                        this.refreshView();
                      }

              )
      )
    },
    methods: {
      selectAlbum: function() {
        if(this.$route != null && this.$route.params != null && this.$route.params.id != null) {
          console.info("Route specified");
          let id = this.$route.params.id;
          if (id > 0) {
            this.selectedAlbum = id;
          }
        }
        else {
          console.info("Route not specified. Try to select first album.");
          if (this.options.count > 0) {
            console.info("Route not specified. Albums count is:", this.options.count);
            this.selectedAlbum = this.options[0].value;
          }
        }
      },

      loadPicturesInSelectedAlbum: function() {
        console.info(this.selectedAlbum);

        this.pictures.length = 0;

        if(this.selectedAlbum != null) {
          let url = `photoAlbumPictures/${this.selectedAlbum}`;
          this.$http.get(url).then(result =>
                  result.json().then(data => {
                            data.forEach(PictureModel => this.pictures.push(PictureModel));
                          }
                  ).then(this.refreshView)
              )
         }
      },

      refreshView: function()
      {
        this.show = false;
        this.$nextTick(() => {
          this.show = true;
        })
      },

      onPhotoAlbumChange: function (event) {
        this.loadPicturesInSelectedAlbum();
        this.refreshView();
      }
    }
  }
</script>
