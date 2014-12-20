skel.init({
    reset: 'full',
    breakpoints: {
      large: {
        media: '(min-width: 1025px) and (max-width: 1280px)',
        containers: 960
      },
      medium: {
        media: '(min-width: 769px) and (max-width: 1024px)',
        containers: '90%'
      },
      small: {
        media: '(max-width: 768px)',
        containers: '95%!'
      },
      xsmall: {
        media: '(max-width: 640px)',
        grid: {                
          collapse: true
        }
      }
    },
    plugins: {
      layers: {
        titleBar: {
          position: 'top-left',
          width: '100%',
          height: 44,
          html: '<div class="toggle" data-action="toggleLayer" data-args="navPanel">&equiv;</div>' +
          '<span<span class="title">Media Naranja</span>'
        },
        navPanel: {
          position: 'top-left',
          width: 300,
          height: '100%',
          orientation: 'vertical',
          side: 'left',
          hidden: true,
          animation: 'pushX',
          clickToHide: true,
          html: '<a href="#"><img src="@routes.Assets.at("images/LogoNaranjaIcono.ico")"/>Home</a>' +
          '<a href="#">Features</a>'
        }
      }
    }
  });
