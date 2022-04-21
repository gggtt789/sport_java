const createBody = function (elemInfo) {
    const interestingFields = {
        'id': 'id',
        'name': 'Имя',
        'birthday': 'Дата создания команды',
        'members': 'Количество людей в команде',
        'sport': 'Вид спорта',
    };
    const nullInfoPlaceholders = {
        'members': 'Информация уточняется',
    };
    const list = document.createElement('ul');
    list.classList.add('list-group');
    for (let key of Object.keys(interestingFields)) {
        const info = document.createElement('li');
        info.classList.add('list-group-item');
        let value = elemInfo[key];
        value = (value === null) ? nullInfoPlaceholders[key] :
            (key === 'members') ? value.length : value;
        value = (key === 'members') && (elemInfo['is_member']) ? 1 : value;
        info.innerHTML = `
            ${interestingFields[key]}: ${value}
        `;
        if (key === 'id') {
            info.setAttribute('id-holder', null);
        }
        list.append(info);
    }

    let link = `/pages/teams/${elemInfo.id}`;
    const info = document.createElement('li');
    info.classList.add('list-group-item');
    info.innerHTML = `
        <a href="${link}">
            Ссылка на страницу команды.
        </a>
    `;

    list.append(info);

    return list;
};

const getAddTeamForm = function () {
    const form = document.createElement('form');
    form.innerHTML = `
        <div class="mb-3">
            <label for="nameTeamAddInput" class="form-label">Имя команды</label>
            <input type="text" class="form-control" id="nameTeamAddInput" aria-describedby="nameHelp" name="name" required>
            <div id="nameHelp" class="form-text">Поле обязательно к заполнению</div>
        </div>
        <div class="mb-3">
            <label for="sportTeamAddInput" class="form-label">Вид спорта</label>
            <input type="text" class="form-control" id="sportTeamAddInput" aria-describedby="sportHelp" name="sport" required>
            <div id="sportHelp" class="form-text">Поле обязательно к заполнению</div>
        </div>
        <div class="mb-3">
            <label for="birthdayTeamAddInput" class="form-label">Дата создания команды</label>
            <input type="date" class="form-control" id="birthdayTeamAddInput" aria-describedby="birthdayHelp" name="birthday" required>
            <div id="birthdayHelp" class="form-text">Поле обязательно к заполнению</div>
        </div>
        <div class="mb-3">
            <label for="isMemberTeamAddInput" class="form-label">Представлена ли команда одиночным спортсменом?</label>
            <input type="checkbox" id="isMemberTeamAddInput" aria-describedby="isMemberHelp" name="is_member">
        </div>
        <button type="submit" class="btn btn-primary">Добавить</button>
    `;
    return form;
};