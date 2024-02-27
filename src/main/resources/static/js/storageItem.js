document.addEventListener('click', (e) => {
    if (e.target.classList.contains('useBtn')) {
        let storageId = e.target.dataset.storageid
        let data = {
            memberId: memberId,
            storageId: storageId
        }

        useItemInStorage(data).then(result => {
            pokemonNames = [];
            for (pokemon of result) {
                pokemonNames.push(pokemon.name)
            }
            alert(pokemonNames + " 당첨")
            spreadItems()
        })
    }
})

async function useItemInStorage(data) {
    try {
        const url = "/storage/use-item"
        const config = {
            method: "post",
            headers: {
                "content-type": "application/json; charset=utf-8"
            },
            body: JSON.stringify(data)
        }
        const resp = await fetch(url, config)
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error)
    }
}

function spreadItems() {
    getItemListFromServer(memberId).then(result => {
        let itemBox = document.getElementById('itemBox')
        itemBox.innerHTML = ''
        if (result.length > 0) {
            for (let item of result) {
                itemBox.innerHTML += `
                <p>상품명 : ${item.name}</p>
                <button data-storageid="${item.storageId}" class="useBtn">사용하기</button>
                <hr>
                `
            }
        } else {
            itemBox.innerHTML = `<p>보유중인 상품이 없습니다.</p>`
        }
    })
}

async function getItemListFromServer(memberId) {
    try {
        const url = "/storage/item-list/" + memberId
        const resp = await fetch(url)
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error)
    }
}
spreadItems()






