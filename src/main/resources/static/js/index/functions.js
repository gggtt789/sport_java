'use strict';


const createBody = function (elemInfo) {
    const interestingFields = {
        'id': 'id',
        'name': 'Название',
        'venue': 'Место проведения',
        'min_price': 'Минимальная цена билета',
        'start_at': 'Дата проведения',
        'seats': 'Количество свободных мест',
        'teams': 'Количество команд',
        'sport': 'Вид спорта',
    };
    const nullInfoPlaceholders = {
        'min_price': 'К сожалению, билетов нет',
        'start_at': 'Неизвестно',
        'seats': '0',
        'teams': 'На данное событие пока не зарегистрирована ни одна команда.',
        'sport': 'Информация устанавливается',
    };
    const list = document.createElement('ul');
    list.classList.add('list-group');
    for (let key of Object.keys(interestingFields)) {
        const info = document.createElement('li');
        info.classList.add('list-group-item');
        let value = elemInfo[key];
        value = (value === null) ? nullInfoPlaceholders[key] :
            (key === 'seats' || key === 'teams') ? value.length :
                (key === 'start_at') ?
                    `${value.substring(8, 10)}.${value.substring(5, 7)}.${value.substring(0, 4)} ${value.substring(11, 16)}` :
                    value;
        info.innerHTML = `
            ${interestingFields[key]}: ${value}
        `;
        if (key === 'id') {
            info.setAttribute('id-holder', null);
        }
        list.append(info);
    }

    let link = `/pages/events/${elemInfo.id}`;
    const info = document.createElement('li');
    info.classList.add('list-group-item');
    info.innerHTML = `
        <a href="${link}">
            Ссылка на страницу события.
        </a>
    `;

    list.append(info);

    return list;
}

const getAddEventForm = function () {
    const form = document.createElement('form');
    form.innerHTML = `
        <div class="mb-3">
            <label for="nameEventAddInput" class="form-label">Название события</label>
            <input type="text" class="form-control" id="nameEventAddInput" aria-describedby="nameHelp" name="name" required>
            <div id="nameHelp" class="form-text">Поле обязательно к заполнению</div>
        </div>
        <div class="mb-3">
            <label for="venueEventAddInput" class="form-label">Место проведения события</label>
            <input type="text" class="form-control" id="venueEventAddInput" aria-describedby="venueHelp" name="venue" required>
            <div id="venueHelp" class="form-text">Поле обязательно к заполнению</div>
        </div>
        <div class="mb-3">
            <label for="resultEventAddInput" class="form-label">Результат события</label>
            <input type="text" class="form-control" id="resultEventAddInput" aria-describedby="resultHelp" name="result" required>
            <div id="resultHelp" class="form-text">Поле обязательно к заполнению</div>
        </div>
        <div class="mb-3">
            <label for="dateEventAddInput" class="form-label">Дата проведения события</label>
            <input type="datetime-local" class="form-control" id="dateEventAddInput" aria-describedby="dateHelp" name="start_at" required>
            <div id="dateHelp" class="form-text">Поле обязательно к заполнению</div>
        </div>
        <button type="submit" class="btn btn-primary">Добавить</button>
    `;
    return form;
};