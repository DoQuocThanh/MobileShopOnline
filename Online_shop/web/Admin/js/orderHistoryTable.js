

function check() {
  let text;
  if (confirm("Are you sure to change status this account") === false) {
    return false;
  } else {
    return true;
  }
}