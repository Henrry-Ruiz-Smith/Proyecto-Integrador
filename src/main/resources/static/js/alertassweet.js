
function showAlertMini(icon, msg) {
  const Toast = Swal.mixin({
    toast: true,
    position: "top-end",
    showConfirmButton: false,
    timer: 3000,
    timerProgressBar: true,
    didOpen: (toast) => {
      toast.onmouseenter = Swal.stopTimer;
      toast.onmouseleave = Swal.resumeTimer;
    }
  });
  Toast.fire({
    icon: icon,
    title: msg
  });

}

function showAlertGrande(icon, title, msg, redireccion) {
  Swal.fire({
    icon: icon,
    title: title,
    text: msg
  }).then((result) => {
    if (result.isConfirmed) {
      if (redireccion != "") {
        window.location.href = redireccion;
      }
    }
  });
}