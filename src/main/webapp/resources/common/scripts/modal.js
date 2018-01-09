/*
 * Java script functionality for modal elements. This file should only be
 * included in a .jsp file as part of the resource pack "modalResources.jsp".
 * If this is to be included as part of a .html file, modal.css is also
 * required to achieve desired functionality.
 * 
 * Dependencies: modal.css
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (4/6/2017)
 */

/**
 * Creates a div DOM element at the end of the document body to be used as a
 * modal display. This newly created DOM element is dependent on styling
 * found in modal.css. The newly created DOM element is returned after
 * creation.
 * 
 * @param modalEltId ID of the modal element
 */
function createModalContainer(modalEltId) {
	var modalContainer = document.createElement("div");
	modalContainer.classList.add("hidden");
	modalContainer.classList.add("modalContainer");
	modalContainer.setAttribute("id", modalEltId);
	document.body.appendChild(modalContainer);
	return modalContainer;
}