'use strict';

window.addEventListener('DOMContentLoaded', async() => {
    const resource = 'http://localhost:8080/members/get_members/';
    await startPageFilling(resource);
});