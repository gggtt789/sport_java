'use strict';

window.addEventListener('DOMContentLoaded', async () => {
    const resource = 'http://localhost:8080/events/delete_event/'
    await activateDeleterObserver(resource);
});