


function GetDataGridEditIndex(datagrid)
{	
	return $(datagrid).attr('DataGridEditIndex')?$(datagrid).attr('DataGridEditIndex'):undefined;
}

function SetDataGridEditIndex(datagrid,value)
{	
	$(datagrid).attr('DataGridEditIndex',value);
}

function endEditing(datagrid){
	if (GetDataGridEditIndex(datagrid) == undefined){return true}
	if ($(datagrid).datagrid('validateRow', GetDataGridEditIndex(datagrid))){
		$(datagrid).datagrid('endEdit', GetDataGridEditIndex(datagrid));
		SetDataGridEditIndex(datagrid,undefined);
		return true;
	} else {
		return false;
	}
}
		
function getRowIndex(datagrid){
	var row = $(datagrid).datagrid('getSelected');	
	var rowIndex=$(datagrid).datagrid("getRowIndex",row);

//	console.log('rowIndex:'+rowIndex);
	return rowIndex;
}

function DataGridEdit(datagrid){	
	//console.log('DataGridEdit')
	//console.log($(datagrid));
	$(datagrid).datagrid('beginEdit', GetDataGridEditIndex(datagrid));
}
function DataGridSave(datagrid,tableName,tableKey,returlURL,callback){
	if (endEditing(datagrid)){
		var row=$(datagrid).datagrid('getChanges');
		console.log(row);
		if (row.length>0)
		{
			Post(tableName,tableKey,row,returlURL,callback,function(){$(datagrid).datagrid('acceptChanges')});
		}
	}
}

function DataGridDelete(datagrid,tableName,tableKey){
	if (endEditing(datagrid)){
		var row =$(datagrid).datagrid('getSelected');
		
		var n=[{}];
		n[0][tableKey]=row[tableKey];
		n[0]['_Status']='D';		
		Post(tableName,tableKey,n,'',function (){$(datagrid).datagrid('reload')});
	}
}

function onClickRow(index){
	
	if (GetDataGridEditIndex('#'+this.id) != index){
		if (endEditing('#'+this.id)){
			$('#'+this.id).datagrid('selectRow', index);
			SetDataGridEditIndex('#'+this.id,index);
		} else {
			$('#'+this.id).datagrid('selectRow', GetDataGridEditIndex('#'+this.id));
		}
	}
}


function onDblClickRow(rowIndex, rowData) {  
		if (endEditing('#'+this.id)){
			$('#'+this.id).datagrid('selectRow', rowIndex)
						.datagrid('beginEdit', rowIndex);
			SetDataGridEditIndex('#'+this.id,rowIndex);
		} else {
			$('#'+this.id).datagrid('selectRow', GetDataGridEditIndex('#'+this.id));
		}
}  

