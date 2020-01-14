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
<!--
    <b-container fluid class="p-4 bg-dark">
      <b-row>
        <b-col>
          <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=54" alt="Image 1"></b-img>
        </b-col>
        <b-col>
          <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=58" alt="Image 2"></b-img>
        </b-col>
        <b-col>
          <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=59" alt="Image 3"></b-img>
        </b-col>
        <b-col>
          <b-img-lazy v-bind="mainProps" :src="getImageUrl(80)" alt="Image 1"></b-img-lazy>
        </b-col>
        <b-col>
          <b-img-lazy v-bind="mainProps" :src="getImageUrl(82)" alt="Image 2"></b-img-lazy>
        </b-col>
        <b-col>
          <b-img-lazy v-bind="mainProps" :src="getImageUrl(84)" alt="Image 3"></b-img-lazy>
        </b-col>
        <b-col>
          <b-img-lazy v-bind="mainProps" :src="getImageUrl(85)" alt="Image 4"></b-img-lazy>
        </b-col>
        <b-col>
          <b-img-lazy v-bind="mainProps" :src="getImageUrl(88)" alt="Image 5"></b-img-lazy>
        </b-col>
        <b-col>
          <b-img-lazy v-bind="mainProps" :src="getImageUrl(90)" alt="Image 6"></b-img-lazy>
        </b-col>
        <b-col>
          <b-img-lazy v-bind="mainProps" :src="getImageUrl(92)" alt="Image 7"></b-img-lazy>
        </b-col>
        <b-col>
          <b-img-lazy v-bind="mainProps" :src="getImageUrl(94)" alt="Image 8"></b-img-lazy>
        </b-col>

      </b-row>

    </b-container>
      -->
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
                      }
              )
      )
    },
    methods: {
      getImageUrl(imageId) {
        const {width, height} = this.mainProps;
        return `https://picsum.photos/${width}/${height}/?image=${imageId}`
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
