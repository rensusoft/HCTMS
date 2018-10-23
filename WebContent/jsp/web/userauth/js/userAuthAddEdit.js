
function validate(form) {
	var action = $('#action').val();
	if (action == 'add') {
		var roleId = $('#roleId').val();
		if (roleId==-1) {
			alert('请选择角色！');
			return false;
		}
		var orgaId=$('#orgaId').val();
		if(orgaId==-1){
			alert('请选择组织！');
			return false;
		}
		
	}
	return true;
}