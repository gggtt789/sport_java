'use strict';

window.addEventListener('DOMContentLoaded', async () => {
    const resource = 'http://localhost:8080/members/get_members/';
    const container = document.querySelector('div.accordion');
    const listFilters = document.querySelector('ul.filters');
    const idFilter = document.querySelector('li[member-id]');
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

    const nameFilter = document.querySelector('li[member-name]');
    nameFilter.addEventListener('click', (event) => {
        const form = nameFilter.querySelector('form');
        if (form !== null && event.target === nameFilter) {
            form.remove();
            listFilters.classList.add('small-width');
            listFilters.classList.remove('mid-width');
            return;
        }
        if (form === null) {
            const inputForm = getFilterForm('memberNameFilterForm', 'memberNameFilterFormInput', 'text', 'name');
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

    const teamNameFilter = document.querySelector('li[team-name]');
    teamNameFilter.addEventListener('click', (event) => {
        const form = teamNameFilter.querySelector('form');
        if (form !== null && event.target === teamNameFilter) {
            form.remove();
            listFilters.classList.add('small-width');
            listFilters.classList.remove('mid-width');
            return;
        }
        if (form === null) {
            const inputForm = getFilterForm('teamNameFilterForm', 'teamNameFilterFormInput', 'text', 'team_name');
            listFilters.classList.add('mid-width');
            listFilters.classList.remove('small-width');
            teamNameFilter.append(inputForm);
            const form = teamNameFilter.querySelector('form')
            form.addEventListener('submit', async (event) => {
                event.preventDefault();
                await filterWithQuery(resource, form, container);
                form.remove();
            });
        }
    });

    const roleFilter = document.querySelector('li[member-role]');
    roleFilter.addEventListener('click', (event) => {
        const form = roleFilter.querySelector('form');
        if (form !== null && event.target === roleFilter) {
            form.remove();
            listFilters.classList.add('small-width');
            listFilters.classList.remove('mid-width');
            return;
        }
        if (form === null) {
            const inputForm = getFilterForm('roleFilterForm', 'roleFilterForm', 'text', 'role');
            listFilters.classList.add('mid-width');
            listFilters.classList.remove('small-width');
            roleFilter.append(inputForm);
            const form = roleFilter.querySelector('form');
            form.addEventListener('submit', async (event) => {
                event.preventDefault();
                await filterWithQuery(resource, form, container);
                form.remove();
            });
        }
    });
});