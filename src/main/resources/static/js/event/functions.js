'use strict';


const getData = async function () {
    const arr = window.location.href.split('/');
    const memberId = arr[arr.length - 1];
    let resource = `/events/get_event/${memberId}`;

    return await (await fetch(resource)).json();
}


const pageFilling = function(data) {
    document.querySelector('h2[name-holder]').innerHTML = `${data.name}`;
    document.querySelector('div[id-holder]').innerHTML = `id: ${data.id}`;
    document.querySelector('div[place-holder]').innerHTML = `место: ${data.venue}`;

    const result = (data.result) ? data.result : 'Неизвестен';
    document.querySelector('div[result-holder]').innerHTML = `итог: ${result}`;

    const time = (!data.start_at) ? 'Уточняется' : `${data.start_at.substring(8, 10)}.${data.start_at.substring(5, 7)}.${data.start_at.substring(0, 4)} ${data.start_at.substring(11, 16)}`;
    document.querySelector('div[time-holder]').innerHTML = `время: ${time}`;
};

const formLinks = function (data, objects, container, emptyFilling) {
    if (!data[objects]) {
        container.textContent = emptyFilling;
        return;
    }
    container.textContent = '';
    data[objects].forEach(elem => {
        if (elem.name !== 'Данные удалены') {
            const li = document.createElement('li');
            li.classList.add('list-group-item');
            li.innerHTML = `
                <a href="/pages/${objects}/${elem['id']}">
                    ${elem['name']}
                </a>
           `;
            container.append(li);
        }
    });
}

const formTickets = function (data) {
    const beforeTickets = document.querySelector('div[before-tickets]');
    const seats = data['seats'];
    if (!seats) {
        const row = document.createElement('div');
        row.classList.add('row');
        row.innerHTML = `Информация о билетах уточняется`;
        beforeTickets.after(row);
        return;
    }
    seats.forEach(elem => {
        const row = document.createElement('div');
        row.classList.add('row');
        row.innerHTML = `
            <div class="col-4 d-flex justify-content-center">
                тип места: ${elem['name']}
            </div>
            <div class="col-4 d-flex justify-content-center">
                цена: ${elem['price']}
            </div>
            <div class="col-4 d-flex justify-content-center">
                количество свободных мест: ${elem['available_number']}
            </div>
        `;
        beforeTickets.after(row);
    });
}

const getEventEditForm = function () {
    const idHolder = document.querySelector('div[id-holder]');
    const id = idHolder.textContent.substring(4, idHolder.textContent.length);
    let form = document.createElement('form');
    form.setAttribute('id', 'EditEventForm');
    form.innerHTML = `
        <div class="mb-3">
            <label for="eventNameInput" class="form-label">Название события</label>
            <input type="text" class="form-control" id="eventNameInput" aria-describedby="nameHelp" name="name">
            <div id="nameHelp" class="form-text">Пожалуйста, введите название события</div>
        </div>
        <div class="mb-3">
            <label for="eventVenueInput" class="form-label">Место проведения</label>
            <input type="text" class="form-control" id="eventVenueInput" aria-describedby="venueHelp" name="venue">
            <div id="venueHelp" class="form-text">Пожалуйста, местоп проведения события</div>
        </div>
        <div class="mb-3">
            <label for="eventStartInput" class="form-label">Дата начала события</label>
            <input type="datetime-local" class="form-control" id="eventStartInput" aria-describedby="startHelp" name="start_at">
            <div id="startHelp" class="form-text">Пожалуйста, введите дату начала события</div>
        </div>
        <div class="mb-3">
            <label for="deleteIdsInput" class="form-label">ID удаляемых событий</label>
            <input type="text" class="form-control" id="deleteIdsInput" aria-describedby="delIdsHelp" name="teams_ids_on_delete">
            <div id="delIdsHelp" class="form-text">Пожалуйста, введите id команд, которые хотите удалить из события</div>
        </div>
        <div class="mb-3">
            <label for="addIdsInput" class="form-label">ID добавляемых событий</label>
            <input type="text" class="form-control" id="addIdsInput" aria-describedby="addIdsHelp" name="teams_ids">
            <div id="addIdsHelp" class="form-text">Пожалуйста, введите id команд, которые хотите добавить в событие</div>
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
    const bodyContainer = document.querySelector('.modal-body');
    const button = document.querySelector('button#Edit-event-button');
    button.addEventListener('click', () => {
        const modalTitle = document.querySelector('.modal-title');
        modalTitle.textContent = 'Редактирование события';
        bodyContainer.textContent = '';
        bodyContainer.append(getEventEditForm());
    });
}

const addBuyListener = function () {
    const button = document.querySelector('button#buy-button');
    button.addEventListener('click', () => {
        const modalTitle = document.querySelector('.modal-title');
        modalTitle.textContent = 'Получение объектов';
    });
}