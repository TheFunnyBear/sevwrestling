<template>
    <li>
       <div>
           <b>{{ message.messageTitle }}</b>
          <br/>
          <i>{{message.messageText}}</i>
          <br/>
          <div>picture uuid: {{message.pictureUuid}}</div>
       </div>
    </li>
</template>

<script>
    export default {
        props: ['message']
    }
</script>

<style>

</style>