'use strict';

window.addEventListener('DOMContentLoaded', async () => {
    const data = await getData();
    pageFilling(data);
    const listMembers = document.querySelector('ul.list-group[members-holder]');
    const listEvents = document.querySelector('ul.list-group[events-holder]')

    formMembersLinks(data, listMembers);
    formEventsLinks(data, listEvents);
    addEditListener();
});