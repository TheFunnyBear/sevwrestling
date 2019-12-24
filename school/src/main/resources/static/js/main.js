Vue.component('paginate', VuejsPaginate)

Vue.component('messagesItem', {
    props: ['message'],
    template:
        '<li>' +
        '   <div>' +
        '       <b>{{ message.messageTitle }}</b>' +
        '      <br/>' +
        '      <i>{{message.messageText}}</i>' +
        '      <br/>' +
        '      <div>picture uuid: {{message.pictureUuid}}</div>'+
        '   </div>' +
        '</li>'
});



Vue.component('messagesPaginator', {
    props: ['totalPages', 'currentPage', 'clickCallback'],
    template:
    '  <paginate :page-count="totalPages" :initial-page="currentPage" :click-handler="clickCallback" :container-class="\'pagination\'" :page-class="\'page-item\'" >'+
    '  </paginate>',

});


Vue.component('messagesList', {
    props: ['messages', 'totalPages', 'currentPage', 'clickCallback'],
    template:
        '<div>' +
        '   <h3>Это список сообщений:</h3>\n' +
        '   <div id="app-4">\n' +
        '      <ol>\n' +
        '         <messagesItem v-for="message in messages" v-bind:key="message.id" :message="message"/>\n' +
        '      </ol>\n' +
        '   </div>' +
        '   <messagesPaginator :totalPages="totalPages" :currentPage="currentPage" :clickCallback="clickCallback" />' +
        '</div>'
        ,
    created: function () {
        let url = `/api/messages/${this.currentPage}/`;
        this.$http.get(url).then(result =>
            result.json().then(data => {
                    console.log(data);
                    this.currentPage = data.pageable.pageNumber;
                    //this.totalPages = data.totalPages;
                    data.content.forEach(message => this.messages.push(message));
                }
            )
        )
    },
    watch: {
        currentPage: function() {
            let url = `/api/messages/${val}/`;
            this.$http.get(url).then(result =>
                result.json().then(data => {
                        console.log(data);
                        this.currentPage = data.pageable.pageNumber;
                        //this.totalPages = data.totalPages;
                        data.content.forEach(message => this.messages.push(message));
                    }
                )
            )
        }
    }
});



var app = new Vue({
    el: '#app',
    template: '<messagesList :messages="messages"  :totalPages="totalPages" :currentPage="currentPage" :clickCallback="clickCallback"/>',
    data: {
        currentPage: 0,
        totalPages: 5,
        clickCallback: function (pageNum) { this.currentPage = pageNum; console.log(pageNum);},
        messages: []
    }
});

<!--
messages: [
    {id: '1', labelMessage: 'message 1', textMessage: 'Привет, Vue! 1'},
    {id: '2', labelMessage: 'message 2', textMessage: 'Привет, Vue! 2'},
    {id: '3', labelMessage: 'message 3', textMessage: 'Привет, Vue! 3'},
]
-->