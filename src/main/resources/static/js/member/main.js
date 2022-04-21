'use strict';

window.addEventListener('DOMContentLoaded', async () => {
    const data = await getData();
    pageFilling(data);
    addEditListener();

    const list = document.querySelector('ul.list-group');
    if (data['events_templates'] === null) {
        list.textContent = 'Отсутствуют';
        return
    }
    list.textContent = '';
    data['events_templates'].forEach(elem => {
        const li = document.createElement('li');
        li.classList.add('list-group-item');
        li.innerHTML = `
            <a href="/pages/events/${elem['id']}">
                ${elem['name']}
            </a>
       `;
        list.append(li);
    });
});