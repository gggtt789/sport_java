'use strict';

window.addEventListener('DOMContentLoaded', async () => {
    const addButton = document.querySelector('button#Add-event-button');
    const modalTitle = document.querySelector('.modal-title');
    const modalBody = document.querySelector('div.modal-body');
    addButton.addEventListener('click', async () => {
        const addEventForm = getAddEventForm();
        modalTitle.innerText = 'Заполните пожалуйста форму';
        modalBody.innerHTML = ``;
        modalBody.append(addEventForm);
        const form = modalBody.querySelector('form')
        form.addEventListener('submit', async (event) => {
            event.preventDefault();
            const data = new FormData(form);
            const object = {};
            data.forEach((value, key) => {
                object[key] = value;
            });
            const url = 'http://localhost:8080/events/add_event/';
            const success = await postData(object, url);
            if (success) {
                modalBody.innerText = `Событие успешно добавлено!`;
            } else {
                modalBody.innerText = `
                    Извините, не удалось добавить событие.
                    Попробуйте еще раз. Проверьте корректность введеных данных.
                `;
            }
        });
    });
});