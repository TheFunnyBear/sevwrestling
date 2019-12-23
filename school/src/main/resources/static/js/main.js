Vue.component('messagesItem', {
    props: ['message'],
    template:
        '<li>' +
        '   <div>' +
        '       <b>{{ message.labelMessage }}</b>' +
        '      <br/>' +
        '      <i>{{message.textMessage}}</i>' +
        '   </div>' +
        '</li>'
})


Vue.component('messagesList', {
    props: ['messages'],
    template:
        '<div>' +
        '   <h3>Это список сообщений:</h3>\n' +
        '   <div id="app-4">\n' +
        '      <ol>\n' +
        '         <messagesItem v-for="message in messages" v-bind:key="message.id" :message="message"/>\n' +
        '      </ol>\n' +
        '   </div>' +
        '</div>'
})



var app = new Vue({
    el: '#app',
    template: '<messagesList :messages="messages"/>',
    data: {
        messages: [
            {id: '1', labelMessage: 'message 1', textMessage: 'Привет, Vue! 1'},
            {id: '2', labelMessage: 'message 2', textMessage: 'Привет, Vue! 2'},
            {id: '3', labelMessage: 'message 3', textMessage: 'Привет, Vue! 3'},
        ]
    }
});