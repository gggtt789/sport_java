'use strict';

window.addEventListener('DOMContentLoaded', async () => {
    const resource = 'http://localhost:8080/teams/get_teams/';
    const container = document.querySelector('div.accordion');
    const listFilters = document.querySelector('ul.filters');
    const idFilter = document.querySelector('li[team-id]');
    idFilter.addEventListener('click', async (event) => {
        const form = idFilter.querySelector('form');
        if (form !== null && event.target === idFilter) {
            form.remove();
            listFilters.classList.add('small-width');
            listFilters.classList.remove('mid-width');
            return;
        }
        if (form === null) {
            const inputForm = getFilterForm('idFilterForm', 'idFilterFormInput', 'number', 'id');
            listFilters.classList.add('mid-width');
            listFilters.classList.remove('small-width');
            idFilter.append(inputForm);
            const form = idFilter.querySelector('form')
            form.addEventListener('submit', async (event) => {
                event.preventDefault();
                await filterWithQuery(resource, form, container);
                form.remove();
            });
        }
    });

    const nameFilter = document.querySelector('li[name]');
    nameFilter.addEventListener('click', (event) => {
        const form = nameFilter.querySelector('form');
        if (form !== null && event.target === nameFilter) {
            form.remove();
            listFilters.classList.add('small-width');
            listFilters.classList.remove('mid-width');
            return;
        }
        if (form === null) {
            const inputForm = getFilterForm('nameFilterForm', 'nameFilterFormInput', 'text', 'name');
            listFilters.classList.add('mid-width');
            listFilters.classList.remove('small-width');
            nameFilter.append(inputForm);
            const form = nameFilter.querySelector('form')
            form.addEventListener('submit', async (event) => {
                event.preventDefault();
                await filterWithQuery(resource, form, container);
                form.remove();
            });
        }
    });

    const sportFilter = document.querySelector('li[sport]');
    sportFilter.addEventListener('click', (event) => {
        const form = sportFilter.querySelector('form');
        if (form !== null && event.target === sportFilter) {
            form.remove();
            listFilters.classList.add('small-width');
            listFilters.classList.remove('mid-width');
            return;
        }
        if (form === null) {
            const inputForm = getFilterForm('sportFilterForm', 'sportFilterFormInput', 'text', 'sport');
            listFilters.classList.add('mid-width');
            listFilters.classList.remove('small-width');
            sportFilter.append(inputForm);
            const form = sportFilter.querySelector('form')
            form.addEventListener('submit', async (event) => {
                event.preventDefault();
                await filterWithQuery(resource, form, container);
                form.remove();
            });
        }
    });

    const birthdayFilter = document.querySelector('li[birthday]');
    birthdayFilter.addEventListener('click', (event) => {
        const form = birthdayFilter.querySelector('form');
        if (form !== null && event.target === birthdayFilter) {
            form.remove();
            listFilters.classList.add('small-width');
            listFilters.classList.remove('mid-width');
            return;
        }
        if (form === null) {
            const inputForm = getFilterForm('birthdayFilterForm', 'birthdayFilterFormInput', 'date', 'birthday');
            listFilters.classList.add('mid-width');
            listFilters.classList.remove('small-width');
            birthdayFilter.append(inputForm);
            const form = birthdayFilter.querySelector('form');
            form.addEventListener('submit', async (event) => {
                event.preventDefault();
                await filterWithQuery(resource, form, container);
                form.remove();
            });
        }
    });
});