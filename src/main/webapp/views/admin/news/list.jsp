<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/common/taglib.jsp"%>
<html>
<c:url var="APIurl" value="/api-admin-news" />
<c:url var="NewsURL" value="/admin-news" />
<head>
<meta charset="UTF-8">
<title>Danh sách bài viết</title>
</head>

<body>


	<div id="layoutSidenav_content">
		<main>
			<form id="formSubmit" method="get"
				action='<c:url value="/admin-news"/>'>
				<div class="container-fluid px-4">
					<h1 class="mt-4">List News</h1>

					

					<div class="card mb-4">
						<div class="card-header">
							<i class="fas fa-table me-1"></i> DataTable Example
						</div>

						<div class="card-body">
							<div
								class="datatable-wrapper datatable-loading no-footer sortable searchable fixed-columns">
								<div class="datatable-top">
									<div class="datatable-dropdown">
										<label> số bản ghi <select class="datatable-selector"
											id="recordNumber" name="recordNumber">
												<option value="2">2</option>
												<option value="5">5</option>
												<option value="10">10</option>
												<option value="15">15</option>
										</select>

										</label>
									</div>

									<div class="edit-btn">
										<a class="btn" title="thêm bài viết"
											href='<c:url value="/admin-news?type=edit"/>'
											style="color: #fff; background-color: DodgerBlue; border: none; color: white; padding: 12px 16px; font-size: 16px; cursor: pointer;">
											<i class="fa fa-plus"></i>
										</a>
										<button class="btn" id="btnDelete" title="xóa bài viết"
											style="color: #fff; background-color: DodgerBlue; border: none; color: white; padding: 12px 16px; font-size: 16px; cursor: pointer;">
											<i class="fa fa-trash"></i>
										</button>
									</div>

								</div>
								<div class="datatable-container">
									<table id="datatablesSimple" class="datatable-table">
										<thead>
											<tr>
												<th><input type="checkbox" id="checkAll"></th>
												<th>Tên bài viết</th>
												<th>Mô tả ngắn</th>
												<th>Thao tác</th>

											</tr>
										</thead>
										<tbody>
											<c:forEach var="item" items="${model.listResult}">
												<tr>
													<c:url var="editUrl" value="/admin-news">
														<c:param name="type" value="edit" />
														<c:param name="id" value="${item.id}" />
														<c:param name="categoryCode" value="${item.categoryCode}" />
													</c:url>
													<td><input type="checkbox" id="checbox_${item.id}"
														value="${item.id}"></td>
													<td>${item.title}</td>
													<td>${item.shortDescription}</td>
													<td><a href='${editUrl}' class="btn btn-info btn-sm"
														title="cập nhật bài viết"><i
															class="fa-solid fa-pen-to-square"></i> Edit </a></td>
												</tr>
											</c:forEach>

										</tbody>
									</table>
								</div>
								<div class="datatable-bottom">
									<div class="container">
										<ul class="pagination" id="pagination"></ul>
										<input type="hidden" value="${model.page}" id="page" name="page" /> <input
											type="hidden" value="${model.maxPageItem}" id="maxPageItem" name="maxPageItem" />
										<input type="hidden" value="${model.sortName}" id="sortName"
											name="sortName" /> <input type="hidden"
											value="" id="recordNumberInput"
											name="recordNumber" /> <input type="hidden"
											value="${model.sortBy}" id="sortBy" name="sortBy" /> <input
											type="hidden" value="" id="searchValue" name="searchValue" />
											<input
											type="hidden" value="list"  name="type" />
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</main>
		<%@include file="/common/admin/footer.jsp"%>
	</div>

	<script type="text/javascript">
    $(function () {
        var currentPage= ${model.page};
        var totalPages=${model.totalPage}
        var visiblePages=3
        var sortName=$('#sortName').val()
        var sortBy=$('#sortBy').val()
        var recordNumber= $('#recordNumber')
       	recordNumber.val(${model.recordNumber})
        var maxPageItem=${model.maxPageItem}
        //var pageOld= ${model.page}
       // var offsetOld =  (pageOld -1 ) * ${model.maxPageItem}
     	var totalItem = ${model.totalItem}
     	var searchBtn = $('#searchBtn')
     	var getValueInputAll=function(){
     		//var offsetNew= (pageOld - 1) *  $("#recordNumber").val()
           // $('#recordNumberInput').val($("#recordNumber").val());
        	$("#sortName").val(sortName);
           	$('#sortBy').val(sortBy);
           	$('#page').val(currentPage);
           	$('#searchValue').val($('#search').val());
        	$('#maxPageItem').val($("#recordNumber").val());// đã xong
         }
     	searchBtn.click(function(){
     		getValueInputAll();
     		$('#page').val(1);
         	$('#formSubmit').submit();
         });
        recordNumber.change(function() {
          	getValueInputAll()
          	$('#page').val(1);
        	$('#formSubmit').submit();
        });
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages, // tổng số page = số lượng item trong csdl / số item 1 trang
            visiblePages: 3, // số button 
            startPage:currentPage, // khi reload thì page sẽ điền vào cái này
            onPageClick: function (event, page) {

            }
        }).on('page', function (event, page) { // page này là trang hiện tại (sau khi reload)
        		$('#maxPageItem').val(maxPageItem);// gửi cho server biết số item 1 trang,
        		// vì mới vào nó k biết
        		console.log("maxpageitem la",maxPageItem);
        		getValueInputAll()
     	   		$('#page').val(page); // gửi trang sắp chuyển tới cho server để nhận dữ liệu
               	$("#sortName").val(sortName);
               	$('#sortBy').val(sortBy);
                //$('#recordNumber').val($("#recordNumber").val());
               	$('#formSubmit').submit();
        });
    });
    $('#checkAll').click(function() {
    	if ($('#checkAll').is(":checked")) {
    		$('tbody input[type=checkbox]').prop('checked', true);
    	}else{
    		$('tbody input[type=checkbox]').prop('checked', false);

        	}
        
    });

        
    $('#btnDelete').click(function(e){
    	e.preventDefault();
        var data={}
		var ids=$('tbody input[type=checkbox]:checked').map(function(){
			return $(this).val();
		}).get();
		data["ids"]= ids;
		deleteNews(data);
        });

    function deleteNews(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                window.location.href="${NewsURL}?type=list&maxPageItem=2&page=1&recordNumber=2"
            },
            error: function (error) {
                console.log(error)
            }
        });
    }
</script>
</body>

</html>