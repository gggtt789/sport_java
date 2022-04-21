'use strict';

window.addEventListener('DOMContentLoaded', async () => {
    const resource = 'http://localhost:8080/teams/delete_team/'
    await activateDeleterObserver(resource);
});