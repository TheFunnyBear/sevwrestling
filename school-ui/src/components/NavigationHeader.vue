<template>
    <div>
        <b-navbar toggleable="lg" type="dark" variant="info">
            <!-- Логотип -->
            <b-navbar-brand href="#">
                <img src="https://placekitten.com/g/30/30" alt="Kitten">
            </b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>
                <!-- Основные пункты меню -->
                <b-navbar-nav>

                    <!-- Новости -->
                    <div v-if="isAdminMode">
                        <b-nav-item-dropdown text="Новости">
                            <b-dropdown-item :to="'/news_list'">Просмотр новостей</b-dropdown-item>
                            <b-dropdown-item :to="'/news_add'">Добавить новость</b-dropdown-item>
                            <b-dropdown-item :to="'/news_manage'">Управление новостями</b-dropdown-item>
                        </b-nav-item-dropdown>
                    </div>
                    <div v-else>
                        <b-nav-item :to="'news'">Новости</b-nav-item>
                    </div>

                    <!-- Фото -->
                    <div v-if="isAdminMode">
                        <b-nav-item-dropdown text="Фотографии">
                            <b-dropdown-item :to="'/photos_list'">Просмотр фотографий</b-dropdown-item>
                            <b-dropdown-item :to="'/photos_add'">Добавить фотографию в альбом</b-dropdown-item>
                            <b-dropdown-item :to="'/photos_manage'">Управление фотографиями</b-dropdown-item>
                            <b-dropdown-item :to="'/photoAlbums_add'">Создать альбом фотографий</b-dropdown-item>
                            <b-dropdown-item :to="'/photoAlbums_manage'">Управление альбомами фотографий</b-dropdown-item>
                        </b-nav-item-dropdown>
                    </div>
                    <div v-else>
                        <b-nav-item :to="'photos'">Фото</b-nav-item>
                    </div>

                    <!-- Видео -->
                    <div v-if="isAdminMode">
                        <b-nav-item-dropdown text="Видео">
                            <b-dropdown-item :to="'/video_list'">Просмотр видео</b-dropdown-item>
                            <b-dropdown-item :to="'/video_add'">Добавить видео</b-dropdown-item>
                        </b-nav-item-dropdown>
                    </div>
                    <div v-else>
                        <b-nav-item :to="'video'">Видео</b-nav-item>
                    </div>

                    <!-- Расписание -->
                    <div v-if="isAdminMode">
                        <b-nav-item-dropdown text="Расписание">
                            <b-dropdown-item :to="'/timeTable_show'">Просмотр расписания</b-dropdown-item>
                            <b-dropdown-item :to="'/timeTable_edit'">Редактировать расписание</b-dropdown-item>
                        </b-nav-item-dropdown>
                    </div>
                    <div v-else>
                        <b-nav-item :to="'timeTable'">Расписание</b-nav-item>
                    </div>

                    <!-- Информациия -->
                    <div v-if="isAdminMode">
                        <b-nav-item-dropdown text="Информациия">
                            <b-dropdown-item :to="'/info_show'">Просмотр информации</b-dropdown-item>
                            <b-dropdown-item :to="'/info_edit'">Редактировать информацию</b-dropdown-item>
                        </b-nav-item-dropdown>
                    </div>
                    <div v-else>
                        <b-nav-item :to="'/info'">Информациия</b-nav-item>
                    </div>
                </b-navbar-nav>

                <!-- Навигационные элементы выравненные справа -->
                <b-navbar-nav class="ml-auto">
                    <b-nav-form>
                        <b-form-input size="sm" class="mr-sm-2" placeholder="Поиск"></b-form-input>
                        <b-button size="sm" class="my-2 my-sm-0" type="submit">Поиск</b-button>
                    </b-nav-form>

                    <b-nav-item-dropdown right>
                        <!-- Using 'button-content' slot -->
                        <template v-slot:button-content>
                            <em>Пользователь</em>
                        </template>
                        <b-dropdown-item href="#">Профиль</b-dropdown-item>
                        <b-dropdown-item href="#">Выход</b-dropdown-item>
                    </b-nav-item-dropdown>
                </b-navbar-nav>
            </b-collapse>
        </b-navbar>
    </div>
</template>

<script>
    export default {
        props: ['isAdminMode'],
        name: "NavigationHeader"
    }
</script>

<style scoped>

</style>