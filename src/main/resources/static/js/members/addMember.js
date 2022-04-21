'use strict';

window.addEventListener('DOMContentLoaded', async () => {
    const addButton = document.querySelector('button#Add-member-button');
    const modalTitle = document.querySelector('.modal-title');
    const modalBody = document.querySelector('div.modal-body');
    addButton.addEventListener('click', async () => {
        const addMemberForm = getAddMemberForm();
        modalTitle.innerText = 'Заполните пожалуйста форму';
        modalBody.innerHTML = ``;
        modalBody.append(addMemberForm);
        const form = modalBody.querySelector('form')
        form.addEventListener('submit', async (event) => {
            event.preventDefault();
            const data = new FormData(form);
            const object = {};
            data.forEach((value, key) => {
                object[key] = value;
            });
            const url = 'http://localhost:8080/members/add_member/';
            const success = await postData(object, url);
            if (success) {
                modalBody.innerText = `Спортсмен успешно добавлен!`;
            } else {
                modalBody.innerText = `
                    Извините, не удалось добавить спортсмена.
                    Попробуйте еще раз. Проверьте корректность введеных данных.
                `;
            }
        });
    });
});