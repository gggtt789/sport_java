'use strict';


const getData = async function () {
    const arr = window.location.href.split('/');
    const memberId = arr[arr.length - 1];
    let resource = `/teams/get_team/${memberId}`;

    return await (await fetch(resource)).json();
}


const pageFilling = function(data) {
    document.querySelector('h2[name-holder]').innerHTML = `${data.name}`;
    document.querySelector('div[id-holder]').innerHTML = `id: ${data.id}`;
    document.querySelector('div[sport-holder]').innerHTML = `вид спорта: ${data.sport}`;
};

const formMembersLinks = function (data, list) {
    if (!data['members'] || data['members'].length === 0) {
        list.textContent = 'Информация уточняется';
        return;
    }
    list.textContent = '';
    data['members'].forEach(elem => {
        const li = document.createElement('li');
        li.classList.add('list-group-item');
        li.innerHTML = `
            <a href="/pages/members/${elem['id']}">
                ${elem['name']}
            </a>
       `;
        list.append(li);
    });
}

const formEventsLinks = function (data, list) {
    if (!data['events']) {
        list.textContent = 'Отсутствуют';
        return;
    }
    list.textContent = '';
    data['events'].forEach(elem => {
        const li = document.createElement('li');
        li.classList.add('list-group-item');
        li.innerHTML = `
            <a href="/pages/events/${elem['id']}">
                ${elem['name']}
            </a>
       `;
        list.append(li);
    });
}

const getTeamEditForm = function () {
    const idHolder = document.querySelector('div[id-holder]');
    const id = idHolder.textContent.substring(4, idHolder.textContent.length);
    let form = document.createElement('form');
    form.setAttribute('id', 'EditTeamForm');
    form.innerHTML = `
        <div class="mb-3">
            <label for="teamNameInput" class="form-label">Название команды</label>
            <input type="text" class="form-control" id="teamNameInput" aria-describedby="nameHelp" name="name">
            <div id="nameHelp" class="form-text">Пожалуйста, введите название команды, поле является обязательным к заполнению</div>
        </div>
        <div class="mb-3">
            <label for="birthdayTeamInput" class="form-label">Дата создания</label>
            <input type="date" class="form-control" id="birthdayTeamInput" aria-describedby="birthdayHelp" name="birthday">
            <div id="birthdayHelp" class="form-text">Пожалуйста, введите дату создания команды, поле является обязательным к заполнению</div>
        </div>
        <div class="mb-3">
            <label for="deleteIdsInput" class="form-label">ID удаляемых событий</label>
            <input type="text" class="form-control" id="deleteIdsInput" aria-describedby="delIdsHelp" name="events_ids_on_delete">
            <div id="delIdsHelp" class="form-text">Пожалуйста, введите id событий, из которых хотите удалить команду, поле является обязательным к заполнению</div>
        </div>
        <div class="mb-3">
            <label for="addIdsInput" class="form-label">ID добавляемых событий</label>
            <input type="text" class="form-control" id="addIdsInput" aria-describedby="addIdsHelp" name="events_ids">
            <div id="addIdsHelp" class="form-text">Пожалуйста, введите id событий, в которые хотите добавить команду, поле является обязательным к заполнению</div>
        </div>
        <input type="hidden" name="id-holder" value="${id}">
        <button type="submit" class="btn btn-primary">Редактировать</button>
    `;
    return form;
}

const postData = async function (object, url, forempty) {
    forempty.textContent = `Пожалуйста подождите. Связываемся с сервером...`;

    const json = JSON.stringify(object);
    const response = await fetch(url, {
        method: 'POST',
        body: json,
        headers: {
            'Content-Type': 'application/json',
        }
    });
    if (response.status === 200) {
        return 1;
    }
    return 0;
}

const addEditListener = function() {
    const modalTitle = document.querySelector('.modal-title');
    modalTitle.textContent = 'Редактирование команды';
    const bodyContainer = document.querySelector('.modal-body');
    const button = document.querySelector('button#Edit-team-button');
    button.addEventListener('click', () => {
        bodyContainer.textContent = '';
        bodyContainer.append(getTeamEditForm());
    });
}