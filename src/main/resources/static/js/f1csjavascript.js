(function($) {
    "use strict"; // Start of use strict

    // Toggle the side navigation
    $("#sidebarToggle, #sidebarToggleTop").on('click', function(e) {
        $("body").toggleClass("sidebar-toggled");
        $(".sidebar").toggleClass("toggled");
        if ($(".sidebar").hasClass("toggled")) {
            $('.sidebar .collapse').collapse('hide');
        };
    });

    $(window).on("load resize", function() {
        var aspectRatio = $(window).width() / $(window).height();

        if (aspectRatio <= 0.57) {
            $(".sidebar").addClass("toggled");
        };
    }).trigger('resize');

    // JavaScript to handle image selection and preview
    const imagePicker = document.getElementById('imagePicker');
    const previewImage = document.getElementById('previewImage');

    imagePicker.addEventListener('change', function() {
        const selectedImage = imagePicker.files[0];
        if (selectedImage) {
            const reader = new FileReader();
            reader.onload = function(event) {
                previewImage.src = event.target.result;
            };
            reader.readAsDataURL(selectedImage);
            previewImage.style.display = 'block'; // Show the preview image
        } else {
            previewImage.src = ''; // Clear the preview if no image selected
            previewImage.style.display = 'none'; // Hide the preview image
        }
    });

    // Prevent the content wrapper from scrolling when the fixed side navigation hovered over
    $('body.fixed-nav .sidebar').on('mousewheel DOMMouseScroll wheel', function(e) {
        if ($(window).width() > 768) {
            var e0 = e.originalEvent,
                delta = e0.wheelDelta || -e0.detail;
            this.scrollTop += (delta < 0 ? 1 : -1) * 30;
            e.preventDefault();
        }
    });

    // Scroll to top button appear
    $(document).on('scroll', function() {
        var scrollDistance = $(this).scrollTop();
        if (scrollDistance > 100) {
            $('.scroll-to-top').fadeIn();
        } else {
            $('.scroll-to-top').fadeOut();
        }
    });

    // Smooth scrolling using jQuery easing
    $(document).on('click', 'a.scroll-to-top', function(e) {
        var $anchor = $(this);
        $('html, body').stop().animate({
            scrollTop: ($($anchor.attr('href')).offset().top)
        }, 1000, 'easeInOutExpo');
        e.preventDefault();
    });

})(jQuery); // End of use strict