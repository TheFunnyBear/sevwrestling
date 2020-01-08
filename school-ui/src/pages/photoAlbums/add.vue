<template>
  <div>
    <NavigationHeader :isAdminMode="isAdminMode"></NavigationHeader>
    <PageHeader :pageTitle="pageTitle" :pageDescription="pageDescription"/>

    <b-container fluid>
      <b-row align-v="center">
        <b-col align="left" cols="8" align-self="center">
          <b-card header-tag="header" footer-tag="footer">
            <template v-slot:header>
              <h6 class="mb-0">Создание фотоальбома</h6>
            </template>
            <b-form @submit="onSubmit" @reset="onReset" v-if="show">
              <!--Название альбома-->
              <b-form-group
                      id="input-group-1"
                      label="Название альбома:"
                      label-for="input-1"
                      description="Указнный тут заголовок новости будет отображаться в списке новостей и при детальном просмотре новости."
              >
                <b-form-input
                        id="input-1"
                        v-model="form.title"
                        type="text"
                        required
                        placeholder="Укажите тут название альбома"
                ></b-form-input>
              </b-form-group>

              <!--Описание новости-->
              <b-form-group id="input-group-2" label="Описание альбома:" label-for="input-2">
                <b-form-textarea
                        id="input-2"
                        v-model="form.description"
                        rows="5"
                        max-rows="6"
                        placeholder="Укажите тут описание для альбома"
                ></b-form-textarea>
              </b-form-group>

            </b-form>
            <template v-slot:footer>
              <b-row align-v="center">
                <b-col align="right">
                  <b-button type="submit" variant="primary">Добавить фотоальбом</b-button>
                  <b-button type="reset" variant="danger">Очистить</b-button>
                </b-col>
              </b-row>
            </template>
          </b-card>
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

  export default {
    components: {NavigationHeader, PageHeader, PageFooter},
    data() {
      return {
        form: {
          comment: '',
          photoFile: null,
          selectedAlbum: null,
        },
        isAdminMode: true,
        pageTitle: 'Добавить альбом для фотографий',
        pageDescription: 'На этой странице вы можите добавить альбом фотографий.',
        show: true,
      }
    }
  }
</script>
