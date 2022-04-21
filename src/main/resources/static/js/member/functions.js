'use strict';


const getData = async function () {
    const arr = window.location.href.split('/');
    const memberId = arr[arr.length - 1];
    let resource = `/members/get_member/?id=${memberId}`;

    return await (await fetch(resource)).json();
}


const pageFilling = function(data) {
    document.querySelector('h2[name-holder]').innerHTML = `${data.name}`;
    document.querySelector('div[id-holder]').innerHTML = `id: ${data.id}`;
    const role = (data.role === null) ? 'отсутствует' : data.role;
    document.querySelector('div[role-holder]').innerHTML = `роль: ${role}`;

    const memberBirthday = data.birthday;
    const age = Math.floor((new Date().getTime() - new Date(memberBirthday)) / 31536000000);
    document.querySelector('div[age-holder]').innerHTML = `возраст: ${age}`;

    const team = (data.current_team === null || data.current_team.name === 'Данные удалены') ? 'отсутствует' : data.current_team.name;
    document.querySelector('div[team-holder]').innerHTML = `команда: ${team}`;
};

const getMemberEditForm = function () {
    const idHolder = document.querySelector('div[id-holder]');
    const id = idHolder.textContent.substring(4, idHolder.textContent.length);
    let form = document.createElement('form');
    form.setAttribute('id', 'EditMemberForm');
    form.innerHTML = `
        <div class="mb-3">
            <label for="memberNameInput" class="form-label">Имя спортсмена</label>
            <input type="text" class="form-control" id="memberNameInput" aria-describedby="nameHelp" name="name" required>
            <div id="nameHelp" class="form-text">Пожалуйста, введите имя спортсмена, поле является обязательным к заполнению</div>
        </div>
        <div class="mb-3">
            <label for="birthdayMemberInput" class="form-label">Дата рождения</label>
            <input type="date" class="form-control" id="birthdayMemberInput" aria-describedby="birthdayHelp" name="birthday" required>
            <div id="birthdayHelp" class="form-text">Пожалуйста, введите дату рождения спортсмена, поле является обязательным к заполнению</div>
        </div>
        <input type="hidden" name="id-holder" value="${id}">
        <button type="submit" class="btn btn-primary">Редактировать</button>
    `;
    return form;
}

const addEditListener = function() {
    const modalTitle = document.querySelector('.modal-title');
    modalTitle.textContent = 'Редактирование спортсмена';
    const bodyContainer = document.querySelector('.modal-body');
    const button = document.querySelector('button#Edit-member-button');
    button.addEventListener('click', () => {
        bodyContainer.textContent = '';
        bodyContainer.append(getMemberEditForm());
    });
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