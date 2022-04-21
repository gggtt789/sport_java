'use strict';

window.addEventListener('DOMContentLoaded', async() => {
    const resource = 'http://localhost:8080/events/get_events/';
    await startPageFilling(resource);
});