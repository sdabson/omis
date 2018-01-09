function applyToggleReferralCenterFilter(){
	$("#filterControlContainer .titleHeader").click(function(){
		toggleReferralCenterFilter();
	});
	
	$("#facilityReferralCenterFilterMin").click(function(){
		toggleReferralCenterFilter();
	});
}

function applyToggleReferral(){	
	$("#referrals .titleHeader").click(function(){
		toggleReferral($(this));
	});
}

function toggleReferral(zeElement){
	/*Close any actionLists that may be open inside the #referrals div because the locations will be changing. */
	$("#referrals").find(".actionList").removeClass("show");
	$("#referrals").find(".actionList").addClass("hide");
	
	$(zeElement).parent()
		.children("#listContainer")
		.slideToggle({duration:800,
						easing:"easeOutCirc",
						complete:function(){
							var totalAvailable = $("#referrals").height();
							var individualHeight = 0;
							var count = 0;					
							
							$(".referralList").each(function(index){
								if($(this).height() > 25){count = count +1;}
						
							});
							
							individualHeight = ((totalAvailable-80)/count)-10;

							$(".referralList").animate({"max-height":individualHeight},
											{duration:300,width:"easeOutCirc"});

						}}); 
}


function toggleReferralCenterFilter(){
	var closed = $("#facilityReferralCenterFilter").hasClass("filterToolsClosed");

	if(!closed){
		$("#facilityReferralCenterFilter").addClass("filterToolsClosed");
		$("#facilityReferralCenterFilter").animate({left:-201},{
			duration:700,
			width:"easeOutCirc",
			complete:function(){
				$("#facilityReferralCenterFilterMin").animate({left:0},{duration:300,width:"easeOutCirc"});
			}
		});
		
		$("#referrals").animate({left:25},800);

	}else{
		$("#facilityReferralCenterFilter").removeClass("filterToolsClosed");
		
		$("#facilityReferralCenterFilterMin").animate({left:-25},{
			duration:200,
			width:"easeOutCirc",
			complete:function(){
				$("#facilityReferralCenterFilter").animate({left:0},{duration:600,width:"easeOutCirc"});
			}
		});
			
		$("#referrals").animate({left:201},{duration:800,width:"easeOutCirc"});
	}
}

function applyScrollableTable(){
	$("#listContainer .listTable").dataTable({
		"sDom": 'rt',
		"bPaginate":false
	});
}