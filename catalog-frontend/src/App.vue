<template>
    <div class="container">
        <app-form></app-form>
        <hr>
        <app-list></app-list>
    </div>          
</template>

<script>
    import Form from './components/Form.vue';
    import List from './components/List.vue';

    export default {
        components: {
            appForm: Form,
            appList: List
        }
    }
</script>

<style>
</style>
