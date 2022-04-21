'use strict';

window.addEventListener('DOMContentLoaded', async () => {
    const addButton = document.querySelector('button#Add-team-button');
    const modalTitle = document.querySelector('.modal-title');
    const modalBody = document.querySelector('div.modal-body');
    addButton.addEventListener('click', async () => {
        const addEventForm = getAddTeamForm();
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
            object['is_member'] = ('is_member' in object);
            const url = 'http://localhost:8080/teams/add_team/';
            const success = await postData(object, url);
            if (success) {
                modalBody.innerText = `Команда успешно добавлена!`;
            } else {
                modalBody.innerText = `
                    Извините, не удалось добавить команду.
                    Попробуйте еще раз. Проверьте корректность введеных данных.
                `;
            }
        });
    });
});