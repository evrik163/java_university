document.getElementById("findButton").addEventListener("click", function () {
    let searchBarVal = document.getElementById('keyword').value;
    localStorage.setItem('keyword', searchBarVal)
    }
  )
