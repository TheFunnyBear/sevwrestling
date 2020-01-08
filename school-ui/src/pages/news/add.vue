<template>
  <div>
    <NavigationHeader :isAdminMode="isAdminMode"></NavigationHeader>
    <PageHeader :pageTitle="pageTitle" :pageDescription="pageDescription"/>

    <b-container fluid>
      <b-row align-v="center">
        <b-col align="left" cols="8" align-self="center">
          <b-card header-tag="header" footer-tag="footer">
            <template v-slot:header>
              <h6 class="mb-0">Создание новости</h6>
            </template>
            <b-form @submit="onSubmit" @reset="onReset" v-if="show">
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

          </b-form>
            <template v-slot:footer>
              <b-row align-v="center">
                <b-col align="right">
                  <b-button type="submit" variant="primary">Добавить новость</b-button>
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
          publicationDate: null,
          title: '',
          description: '',
          photoFile: null,
          checked: []
        },
        isAdminMode: true,
        pageTitle: 'Добавить новость',
        pageDescription: 'На этой странице вы можите добавить новость.',
        show: true
      }
    },
    methods: {
      onSubmit(evt) {
        evt.preventDefault();
        alert(JSON.stringify(this.form))
      },
      onReset(evt) {
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

<style>
  .custom-file-input:lang(en) ~ .custom-file-label::after {
    content: 'Выбрать файл';
  }
</style>
