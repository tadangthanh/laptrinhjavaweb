<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/common/taglib.jsp"%>
<html>

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
										<label>
										số bản ghi
										<select class="datatable-selector" id="recordNumber">
												<option value="${model.recordNumber}" selected="selected">${model.recordNumber}</option>
												<option value="2">2</option>
												<option value="5">5</option>
												<option value="10">10</option>
												<option value="15">15</option>
										</select>

										</label>
									</div>
									<div class="datatable-search">
										<input class="datatable-input" placeholder="Search..."
											type="search" title="Search within table"
											aria-controls="datatablesSimple">
									</div>
								</div>
								<div class="datatable-container">
									<table id="datatablesSimple" class="datatable-table">
										<thead>
											<tr>
												<th>Tên bài viết</th>
												<th>Mô tả ngắn</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="item" items="${model.listResult}">
												<tr>
													<td>${item.title}</td>
													<td>${item.shortDescription}</td>
												</tr>
											</c:forEach>

										</tbody>
									</table>
								</div>
								<div class="datatable-bottom">
									<div class="container">
										<ul class="pagination" id="pagination"></ul>
										<input type="hidden" value="" id="page" name="page" />
										<input type="hidden" value="" id="maxPageItem" name="maxPageItem" />
										<input type="hidden" value="${model.sortName}" id="sortName" name="sortName"/>
										<input type="hidden" value="${model.recordNumber}" id="recordNumberInput" name="recordNumber"/>
										<input type="hidden" value="${model.sortBy}" id="sortBy" name="sortBy"/>
										<input type="hidden" value="" id="searchValue" name="searchValue"/>
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
        var maxPageItem=${model.maxPageItem}
        var pageOld= ${model.page}
        var offsetOld =  (pageOld -1 ) * ${model.maxPageItem}
     	var totalItem = ${model.totalItem}
     	var searchBtn = $('#searchBtn')
     	var getValueInputAll=function(){
     		var offsetNew= (pageOld - 1) *  $("#recordNumber").val()
            $('#recordNumberInput').val($("#recordNumber").val());
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
        		getValueInputAll()
     	   		$('#page').val(page); // gửi trang sắp chuyển tới cho server để nhận dữ liệu
               	$("#sortName").val(sortName);
               	$('#sortBy').val(sortBy);
                $('#recordNumberInput').val($("#recordNumber").val());
               	$('#formSubmit').submit();
        });
    });
</script>
</body>

</html>