module.exports = function(grunt){
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),

        copy: {
            
            t2: {
                src: 'assets/fonts/*',
                dest: 'assets/dist/fonts/'
            }
        },
        less: {
            development: {
              options: {
                compress : true,
                yuicompress : true,
                optimization: 2
              },
              files: {
                "assets/dist/styles/result.min.css": "assets/styles/*.less",
                "assets/dist/styles/bootstrap.min.css":  "assets/less/bootstrap.less",
                "assets/dist/styles/font-awesome.min.css":  "assets/font-awesome/less/font-awesome.less"
              }
            }
        },
        watch: {
              w1: {
                  files: ['assets/**/*.less'],
                  tasks: ['less']
              }
        },
        prettify: {
            options: {
              
            },
            // Prettify a directory of files 
            all: {
              expand: true,
              cwd: 'views/',
              ext: '.html',
              src: ['*.html'],
              dest: 'views/'
            },
        }
    });

    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('assemble-less');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-prettify');
    grunt.registerTask('default',['copy','less','watch',]);
    
};