'use strict';


const getData = async function (resource, eventsContainer, params) {
    if (params.length !== 0) {
        resource += '?';
        for (let key of Object.keys(params)) {
            resource += `${key}=${params[key]}&`
        }
        resource = resource.substring(0, resource.length - 1);
    }
    eventsContainer.innerHTML = `
        <p>
            Пожалуйста подождите. Данные загружаются с сервера...
        </p>
    `;
    const response = await fetch(resource);
    if (response.status !== 200) {
        eventsContainer.innerHTML = ``;
        const errorMsg = getLoadErrorMsg();
        eventsContainer.append(errorMsg);
        return;
    }
    return await response.json();
}

const getAccordionElem = function (elem) {
    const eventItem = document.createElement('div');
    eventItem.classList.add('accordion-item');
    eventItem.innerHTML = `
            <h2 class="accordion-header" id="event-id${elem.id}"> 
                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-${elem.id}" aria-expanded="true" aria-controls="collapse-${elem.id}">
                    ${elem.name}
                </button>
            </h2>
            <div id="collapse-${elem.id}" class="accordion-collapse collapse show" aria-labelledby="event-id${elem.id}" data-bs-parent="#accordionElems
            ">
                <div class="accordion-body">             
                </div>
            </div>
        `;

    const eventBody = eventItem.querySelector('div.accordion-body');
    eventBody.append(createBody(elem));
    const delBtn = document.createElement('button');
    delBtn.classList.add('btn', 'btn-dark');
    delBtn.innerText = 'Удалить';
    delBtn.setAttribute('btn-deleter', null);
    eventBody.append(delBtn);

    return eventItem;
}
const getLoadErrorMsg = function () {
    const errorMsg = document.createElement('p');
    errorMsg.classList.add('load-error');
    errorMsg.innerText = 'Возникла ошибка связи с сервером. Попробуйте повторить действие или перезагрузите страницу.';

    return errorMsg;
};

const getFilterForm = function (idForm, idInput, typeInput, name) {
    const form = document.createElement('form');
    form.setAttribute('id', idForm);
    form.classList.add('form-inline');
    form.innerHTML = `
        <div class="input-group" style="max-width: 300px;">
            <input type="${typeInput}" class="form-control" id="${idInput}" name="${name}">
            <button type="submit" class="btn btn-primary">Найти</button>
        </div>
    `;

    return form;
};

const postData = async function (object, url) {
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
};

const deleteData = async function (resource, id) {
    const url = `${resource}${id}`;
    await fetch(url, {method: "DELETE"});
};

const filterWithQuery = async function (resource, form, container) {
    const data = new FormData(form);
    const object = {};
    data.forEach((value, key) => {
        object[key] = value;
        if (key === 'start_at') {
            object[key] += ':00';
        }
    });

    const info = await getData(resource, container, object);
    if (!info) {
        return;
    }
    container.innerHTML = '';
    if (info.length === 0) {
        container.innerHTML = `Пусто! По вашему запросу ничего не найдено`;
    }
    info.forEach((elem) => {
        const eventItem = getAccordionElem(elem);
        container.append(eventItem);
    });
};

const activateDeleterObserver = async function (resource) {
    const elem = document.querySelector('#accordionElems');
    const observer = new MutationObserver((mutations) => {
        mutations.forEach((mutation) => {
            for(let node of mutation.addedNodes) {
                if (!(node instanceof HTMLElement)) continue;
                if (node.matches('div.accordion-item')){
                    const btn = node.querySelector('button[btn-deleter]');
                    btn.addEventListener('click', async () => {
                        const event_id = node.querySelector('li[id-holder]');
                        const id = parseInt(event_id.innerText.substring(4, event_id.innerText.length));
                        await deleteData(resource, id);
                        node.remove();
                    });
                }
            }
        });
    });
    const config = {childList: true};
    observer.observe(elem, config);
}

const startPageFilling = async function (resource) {
    const container = document.querySelector('div.accordion');
    const data = await getData(resource, container, {});
    if (!data) {
        return;
    }
    container.innerHTML = '';
    data.forEach((elem) => {
        const item = getAccordionElem(elem);
        container.append(item);
    });
}