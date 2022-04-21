const createBody = function (elemInfo) {
    const interestingFields = {
        'id': 'id',
        'name': 'Имя',
        'birthday': 'Дата рождения',
        'role': 'Роль',
        'current_team': 'Текущая команда',
    };
    const nullInfoPlaceholders = {
        'role': 'Информация уточняется',
        'current_team': 'Без команды',
    };
    const list = document.createElement('ul');
    list.classList.add('list-group');
    for (let key of Object.keys(interestingFields)) {
        const info = document.createElement('li');
        info.classList.add('list-group-item');
        let value = elemInfo[key];
        value = (value === null) ? nullInfoPlaceholders[key] :
            (key === 'current_team') ? (elemInfo[key]['name'] === 'Данные удалены') ? 'Без команды' : elemInfo[key]['name'] : value;
        info.innerHTML = `
            ${interestingFields[key]}: ${value}
        `;
        if (key === 'id') {
            info.setAttribute('id-holder', null);
        }
        list.append(info);
    }

    let link = `/pages/members/${elemInfo.id}`;
    const info = document.createElement('li');
    info.classList.add('list-group-item');
    info.innerHTML = `
        <a href="${link}">
            Ссылка на страницу спортсмена.
        </a>
    `;

    list.append(info);

    return list;
};

const getAddMemberForm = function () {
    const form = document.createElement('form');
    form.innerHTML = `
        <div class="mb-3">
            <label for="nameMemberAddInput" class="form-label">Имя спортсмена</label>
            <input type="text" class="form-control" id="nameMemberAddInput" aria-describedby="nameHelp" name="name" required>
            <div id="nameHelp" class="form-text">Поле обязательно к заполнению</div>
        </div>
        <div class="mb-3">
            <label for="birthdayMemberAddInput" class="form-label">Дата рождения</label>
            <input type="date" class="form-control" id="birthdayMemberAddInput" aria-describedby="birthdayHelp" name="birthday" required>
            <div id="birthdayHelp" class="form-text">Поле обязательно к заполнению</div>
        </div>
        <button type="submit" class="btn btn-primary">Добавить</button>
    `;
    return form;
};