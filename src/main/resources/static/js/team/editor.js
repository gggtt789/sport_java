'use strict';

window.addEventListener('DOMContentLoaded', async () => {
    let resource = null;
    const elem = document.querySelector('.modal-body');
    const observer = new MutationObserver(async (mutations) => {
        mutations.forEach((mutation) => {
            for(let node of mutation.addedNodes) {
                if (!(node instanceof HTMLElement)) continue;

                if (node.matches('form#EditTeamForm')){
                    node.addEventListener('submit', async (event) => {
                        event.preventDefault();
                        const data = new FormData(node);
                        const object = {};
                        data.forEach((value, key) => {
                            if (key === 'id-holder') {
                                resource = `http://localhost:8080/teams/update_team/${value}`;
                            } else if (key === 'events_ids_on_delete' || key === 'events_ids') {
                                if (value) {
                                    object[key] = value.split(',');
                                    object[key] = object[key].map((x) => {
                                        return parseInt(x, 10);
                                    });
                                }
                            } else {
                                object[key] = value;
                            }
                        });
                        const success = await postData(object, resource, elem);
                        if (success) {
                            elem.textContent = 'Данные о команде успешно обновлены!'
                        } else {
                            elem.textContent = 'Возникла ошибка. Попробуйте еще раз или перезагрузите страницу';
                        }
                    });
                }
            }
        });
    });
    const config = {childList: true, subtree: true};
    observer.observe(elem, config);
});