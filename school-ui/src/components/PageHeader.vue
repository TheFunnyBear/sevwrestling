<template>
    <b-container fluid>
        <div>
            <b-media right-align vertical-align="center">
                <h2 class="mt-0 mb-1">{{pageTitle}}</h2>
                <p class="mb-0">
                    {{pageDescription}}
                </p>
            </b-media>
        </div>
    </b-container>
</template>

<script>
    export default {
        props: ['pageTitle', 'pageDescription'],
        name: "PageHeader"
    }
</script>

<style scoped>
    body {
        background-color: #6d41001f;
    }
</style>