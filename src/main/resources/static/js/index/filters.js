'use strict';

window.addEventListener('DOMContentLoaded', async () => {
    const resource = 'http://localhost:8080/events/get_events/';
    const eventsContainer = document.querySelector('div.accordion');
    const listFilters = document.querySelector('ul.filters');
    const minToMaxFilter = document.querySelector('li[min-to-max]');
    minToMaxFilter.addEventListener('click', async () => {
        const data = await getData(resource, eventsContainer, {});
        const filteredData = data.filter(elem => elem.min_price !== null);
        filteredData.sort((a, b) => {
            return a.min_price - b.min_price;
        });
        eventsContainer.innerHTML = '';
        filteredData.forEach((elem) => {
            const eventItem = getAccordionElem(elem);
            eventsContainer.append(eventItem);
        });
    });

    const maxToMinFilter = document.querySelector('li[max-to-min]');
    maxToMinFilter.addEventListener('click', async () => {
        const data = await getData(resource, eventsContainer, {});
        const filteredData = data.filter(elem => elem.min_price !== null);
        filteredData.sort((a, b) => {
            return b.min_price - a.min_price;
        });
        eventsContainer.innerHTML = '';
        filteredData.forEach((elem) => {
            const eventItem = getAccordionElem(elem);
            eventsContainer.append(eventItem);
        });
    });

    const venueFilter = document.querySelector('li[venue]');
    venueFilter.addEventListener('click', (event) => {
        const form = venueFilter.querySelector('form');
        if (form !== null && event.target === venueFilter) {
            form.remove();
            listFilters.classList.add('small-width');
            listFilters.classList.remove('mid-width');
            return;
        }
        if (form === null) {
            const inputForm = getFilterForm('venueFilterForm', 'venueFilterFormInput', 'text', 'venue');
            listFilters.classList.add('mid-width');
            listFilters.classList.remove('small-width');
            venueFilter.append(inputForm);
            const form = venueFilter.querySelector('form')
            form.addEventListener('submit', async (event) => {
                event.preventDefault();
                await filterWithQuery(resource, form, eventsContainer);
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
                await filterWithQuery(resource, form, eventsContainer);
                form.remove();
            });
        }
    });

    const dateFilter = document.querySelector('li[date-time]');
    dateFilter.addEventListener('click', (event) => {
        const form = dateFilter.querySelector('form');
        if (form !== null && event.target === dateFilter) {
            form.remove();
            listFilters.classList.add('small-width');
            listFilters.classList.remove('mid-width');
            return;
        }
        if (form === null) {
            const inputForm = getFilterForm('dateFilterForm', 'dateFilterFormInput', 'datetime-local', 'start_at');
            listFilters.classList.add('mid-width');
            listFilters.classList.remove('small-width');
            dateFilter.append(inputForm);
            const form = dateFilter.querySelector('form');
            form.addEventListener('submit', async (event) => {
                event.preventDefault();
                await filterWithQuery(resource, form, eventsContainer);
                form.remove();
            });
        }
    });
});