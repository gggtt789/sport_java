'use strict';

window.addEventListener('DOMContentLoaded', async () => {
    const resource = 'http://localhost:8080/members/delete_member/'
    await activateDeleterObserver(resource);
});