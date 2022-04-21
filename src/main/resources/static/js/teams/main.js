'use strict';

window.addEventListener('DOMContentLoaded', async() => {
    const resource = 'http://localhost:8080/teams/get_teams/';
    await startPageFilling(resource);
});