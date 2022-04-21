'use strict';


window.addEventListener('DOMContentLoaded', async () => {
    const data = await getData();
    pageFilling(data);
    let objects = 'teams';
    let container = document.querySelector('ul[teams-holder]');
    let emptyFilling = 'Отсутствуют'
    formLinks(data, objects, container, emptyFilling);
    objects = 'members';
    container = document.querySelector('ul[members-holder]');
    emptyFilling = 'Отсутствуют'
    formLinks(data, objects, container, emptyFilling);

    formTickets(data);
    addEditListener();
    addBuyListener();
});