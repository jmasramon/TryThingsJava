package transducers;

import com.cognitect.transducers.Fns;
import com.cognitect.transducers.IStepFunction;
import com.cognitect.transducers.ITransducer;
import com.cognitect.transducers.Predicate;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.cognitect.transducers.Fns.*;

public class Playground {

    public static void main(String[] args) {
/*
        var lines = require('transduce/string/lines')
        var logFile = fs.readFileSync('access.log', {encoding: 'utf8'})

        var parseLog = R.compose(
                lines(),
                R.filter(isPage),
                R.map(splitLine),
                R.map(valueToUrl),
                R.map(R.join(' visited ')),
                R.map(R.add(R.__, '\n')))

        var out = R.into('', parseLog, [logFile])
        console.log(out)
*/
        String logFile = "127.0.0.2 - - [26/Feb/2015 19:18:35] \"GET /blog/2014/transducers-explained-1/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:25:25] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /projects/underarm/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/theme.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:27:13] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.4 - - [26/Feb/2015 19:27:34] \"GET /projects/underarm/ HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [26/Feb/2015 19:27:35] \"GET /blog/ HTTP/1.1\" 200 -\n" +
                "127.0.0.3 - - [26/Feb/2015 19:27:35] \"GET /static/img/threedots.png HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [26/Feb/2015 19:27:35] \"GET /blog/2014/transducers-explained-1/ HTTP/1.1\" 200 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:45:35] \"GET /blog/2014/transducers-explained-pipelines/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"GET / HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"POST / HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"GET /static/css/theme.min.css HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [28/Feb/2015 16:44:03] \"GET /static/js/theme.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/img/icons32px.png HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/favicon.ico HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/underarm.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /projects/flask-todomvc HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/theme.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.3 - - [28/Feb/2015 16:44:30] \"GET /static/resources/OlympicAthletes_head.csv HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [22/Mar/2015 18:49:09] \"GET /blog/2015/ramda-transducers-logs/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [22/Mar/2015 18:49:09] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:25:25] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /projects/underarm/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/theme.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:27:13] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.4 - - [26/Feb/2015 19:27:34] \"GET /projects/underarm/ HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [26/Feb/2015 19:27:35] \"GET /blog/ HTTP/1.1\" 200 -\n" +
                "127.0.0.3 - - [26/Feb/2015 19:27:35] \"GET /static/img/threedots.png HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [26/Feb/2015 19:27:35] \"GET /blog/2014/transducers-explained-1/ HTTP/1.1\" 200 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:45:35] \"GET /blog/2014/transducers-explained-pipelines/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"GET / HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"POST / HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"GET /static/css/theme.min.css HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [28/Feb/2015 16:44:03] \"GET /static/js/theme.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/img/icons32px.png HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/favicon.ico HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/underarm.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /projects/flask-todomvc HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/theme.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.3 - - [28/Feb/2015 16:44:30] \"GET /static/resources/OlympicAthletes_head.csv HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [22/Mar/2015 18:49:09] \"GET /blog/2015/ramda-transducers-logs/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [22/Mar/2015 18:49:09] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:25:25] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /projects/underarm/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/theme.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:27:13] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.4 - - [26/Feb/2015 19:27:34] \"GET /projects/underarm/ HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [26/Feb/2015 19:27:35] \"GET /blog/ HTTP/1.1\" 200 -\n" +
                "127.0.0.3 - - [26/Feb/2015 19:27:35] \"GET /static/img/threedots.png HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [26/Feb/2015 19:27:35] \"GET /blog/2014/transducers-explained-1/ HTTP/1.1\" 200 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:45:35] \"GET /blog/2014/transducers-explained-pipelines/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"GET / HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"POST / HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"GET /static/css/theme.min.css HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [28/Feb/2015 16:44:03] \"GET /static/js/theme.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/img/icons32px.png HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/favicon.ico HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/underarm.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /projects/flask-todomvc HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/theme.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.3 - - [28/Feb/2015 16:44:30] \"GET /static/resources/OlympicAthletes_head.csv HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [22/Mar/2015 18:49:09] \"GET /blog/2015/ramda-transducers-logs/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [22/Mar/2015 18:49:09] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:25:25] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /projects/underarm/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/theme.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:27:13] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.4 - - [26/Feb/2015 19:27:34] \"GET /projects/underarm/ HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [26/Feb/2015 19:27:35] \"GET /blog/ HTTP/1.1\" 200 -\n" +
                "127.0.0.3 - - [26/Feb/2015 19:27:35] \"GET /static/img/threedots.png HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [26/Feb/2015 19:27:35] \"GET /blog/2014/transducers-explained-1/ HTTP/1.1\" 200 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:45:35] \"GET /blog/2014/transducers-explained-pipelines/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"GET / HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"POST / HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"GET /static/css/theme.min.css HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [28/Feb/2015 16:44:03] \"GET /static/js/theme.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/img/icons32px.png HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/favicon.ico HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/underarm.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /projects/flask-todomvc HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/theme.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.3 - - [28/Feb/2015 16:44:30] \"GET /static/resources/OlympicAthletes_head.csv HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [22/Mar/2015 18:49:09] \"GET /blog/2015/ramda-transducers-logs/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [22/Mar/2015 18:49:09] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:25:25] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /projects/underarm/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/theme.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:27:13] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.4 - - [26/Feb/2015 19:27:34] \"GET /projects/underarm/ HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [26/Feb/2015 19:27:35] \"GET /blog/ HTTP/1.1\" 200 -\n" +
                "127.0.0.3 - - [26/Feb/2015 19:27:35] \"GET /static/img/threedots.png HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [26/Feb/2015 19:27:35] \"GET /blog/2014/transducers-explained-1/ HTTP/1.1\" 200 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:45:35] \"GET /blog/2014/transducers-explained-pipelines/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"GET / HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"POST / HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"GET /static/css/theme.min.css HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [28/Feb/2015 16:44:03] \"GET /static/js/theme.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/img/icons32px.png HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/favicon.ico HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/underarm.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /projects/flask-todomvc HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/theme.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.3 - - [28/Feb/2015 16:44:30] \"GET /static/resources/OlympicAthletes_head.csv HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [22/Mar/2015 18:49:09] \"GET /blog/2015/ramda-transducers-logs/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [22/Mar/2015 18:49:09] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:25:25] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /projects/underarm/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/theme.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:27:13] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.4 - - [26/Feb/2015 19:27:34] \"GET /projects/underarm/ HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [26/Feb/2015 19:27:35] \"GET /blog/ HTTP/1.1\" 200 -\n" +
                "127.0.0.3 - - [26/Feb/2015 19:27:35] \"GET /static/img/threedots.png HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [26/Feb/2015 19:27:35] \"GET /blog/2014/transducers-explained-1/ HTTP/1.1\" 200 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:45:35] \"GET /blog/2014/transducers-explained-pipelines/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"GET / HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"POST / HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"GET /static/css/theme.min.css HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [28/Feb/2015 16:44:03] \"GET /static/js/theme.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/img/icons32px.png HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/favicon.ico HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/underarm.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /projects/flask-todomvc HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/theme.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.3 - - [28/Feb/2015 16:44:30] \"GET /static/resources/OlympicAthletes_head.csv HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [22/Mar/2015 18:49:09] \"GET /blog/2015/ramda-transducers-logs/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [22/Mar/2015 18:49:09] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:25:25] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /projects/underarm/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/theme.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:27:13] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.4 - - [26/Feb/2015 19:27:34] \"GET /projects/underarm/ HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [26/Feb/2015 19:27:35] \"GET /blog/ HTTP/1.1\" 200 -\n" +
                "127.0.0.3 - - [26/Feb/2015 19:27:35] \"GET /static/img/threedots.png HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [26/Feb/2015 19:27:35] \"GET /blog/2014/transducers-explained-1/ HTTP/1.1\" 200 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:45:35] \"GET /blog/2014/transducers-explained-pipelines/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"GET / HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"POST / HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"GET /static/css/theme.min.css HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [28/Feb/2015 16:44:03] \"GET /static/js/theme.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/img/icons32px.png HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/favicon.ico HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/underarm.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /projects/flask-todomvc HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/theme.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.3 - - [28/Feb/2015 16:44:30] \"GET /static/resources/OlympicAthletes_head.csv HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [22/Mar/2015 18:49:09] \"GET /blog/2015/ramda-transducers-logs/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [22/Mar/2015 18:49:09] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:25:25] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /projects/underarm/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/theme.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:27:13] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.4 - - [26/Feb/2015 19:27:34] \"GET /projects/underarm/ HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [26/Feb/2015 19:27:35] \"GET /blog/ HTTP/1.1\" 200 -\n" +
                "127.0.0.3 - - [26/Feb/2015 19:27:35] \"GET /static/img/threedots.png HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [26/Feb/2015 19:27:35] \"GET /blog/2014/transducers-explained-1/ HTTP/1.1\" 200 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:45:35] \"GET /blog/2014/transducers-explained-pipelines/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"GET / HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"POST / HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"GET /static/css/theme.min.css HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [28/Feb/2015 16:44:03] \"GET /static/js/theme.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/img/icons32px.png HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/favicon.ico HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/underarm.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /projects/flask-todomvc HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/theme.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.3 - - [28/Feb/2015 16:44:30] \"GET /static/resources/OlympicAthletes_head.csv HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [22/Mar/2015 18:49:09] \"GET /blog/2015/ramda-transducers-logs/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [22/Mar/2015 18:49:09] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:25:25] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /projects/underarm/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/theme.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:27:13] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.4 - - [26/Feb/2015 19:27:34] \"GET /projects/underarm/ HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [26/Feb/2015 19:27:35] \"GET /blog/ HTTP/1.1\" 200 -\n" +
                "127.0.0.3 - - [26/Feb/2015 19:27:35] \"GET /static/img/threedots.png HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [26/Feb/2015 19:27:35] \"GET /blog/2014/transducers-explained-1/ HTTP/1.1\" 200 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:45:35] \"GET /blog/2014/transducers-explained-pipelines/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"GET / HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"POST / HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"GET /static/css/theme.min.css HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [28/Feb/2015 16:44:03] \"GET /static/js/theme.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/img/icons32px.png HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/favicon.ico HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/underarm.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /projects/flask-todomvc HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/theme.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.3 - - [28/Feb/2015 16:44:30] \"GET /static/resources/OlympicAthletes_head.csv HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [22/Mar/2015 18:49:09] \"GET /blog/2015/ramda-transducers-logs/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [22/Mar/2015 18:49:09] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:25:25] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:25:25] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /projects/underarm/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/theme.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:27:13] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [26/Feb/2015 19:27:13] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.4 - - [26/Feb/2015 19:27:34] \"GET /projects/underarm/ HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [26/Feb/2015 19:27:35] \"GET /blog/ HTTP/1.1\" 200 -\n" +
                "127.0.0.3 - - [26/Feb/2015 19:27:35] \"GET /static/img/threedots.png HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [26/Feb/2015 19:27:35] \"GET /blog/2014/transducers-explained-1/ HTTP/1.1\" 200 -\n" +
                "127.0.0.2 - - [26/Feb/2015 19:45:35] \"GET /blog/2014/transducers-explained-pipelines/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"GET / HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"POST / HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:03] \"GET /static/css/theme.min.css HTTP/1.1\" 200 -\n" +
                "127.0.0.5 - - [28/Feb/2015 16:44:03] \"GET /static/js/theme.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/img/simplectic_logo.png HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/img/icons32px.png HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:04] \"GET /static/favicon.ico HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:06] \"GET /static/js/underarm.min.js HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /projects/flask-todomvc HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/theme.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/underscore-1.7.0-min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/es6-promise-2.0.0.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/js/underarm.min.js HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [28/Feb/2015 16:44:08] \"GET /static/img/icons32px.png HTTP/1.1\" 304 -\n" +
                "127.0.0.3 - - [28/Feb/2015 16:44:30] \"GET /static/resources/OlympicAthletes_head.csv HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [22/Mar/2015 18:49:09] \"GET /blog/2015/ramda-transducers-logs/ HTTP/1.1\" 200 -\n" +
                "127.0.0.1 - - [22/Mar/2015 18:49:09] \"GET /static/css/theme.min.css HTTP/1.1\" 304 -\n" +
                "127.0.0.1 - - [22/Mar/2015 18:48:30] \"GET /blog/2015/ramda-transducers-logs/ HTTP/1.1\" 200 -";

        String lines[] = logFile.split("\\r?\\n");
        System.out.println("lines = " + lines.length);

        ///////////////////////////////////////////////////////////////////////
        // with streams as baseline

        Stream<String > linesStream = Arrays.stream(lines);

        Function<String, String[]> ipAndUrlOnly = line -> {
            Pattern p = Pattern.compile("^(\\S+).+\"([^\"]+)\"");

            Matcher m = p.matcher(line);
            m.find();
            return new String [] {m.group(1), m.group(2) };

        };

//        System.out.println(Arrays.toString(ipAndUrlOnly.apply("127.0.0.1 - - [22/Mar/2015 18:48:30] \"GET /blog/2015/ramda-transducers-logs/ HTTP/1.1\" 200 -")));

        Function<String, String> valueToUrl = httpCommand -> {
            List<String> partialUrl = new ArrayList<String>(Arrays.asList(httpCommand.split(" ")).subList(1, 2));
            partialUrl.add(0, "https://simplectic.com");

            return String.join("", partialUrl);
        };

//        System.out.println(valueToUrl.apply("GET /blog/2014/transducers-explained-1/ HTTP/1.1"));

        Function<String[], String[]> correctUrl = elem ->{
            return new String[] { elem[0], valueToUrl.apply(elem[1]) };
        };

//        System.out.println(Arrays.toString(
//                correctUrl.apply(new String[] {"127.0.0.1", "GET /blog/2015/ramda-transducers-logs/ HTTP/1.1"})
//        ));

        String[] result = linesStream
                .filter(it -> it.contains("GET /"))
                .filter(it -> !it.contains("GET /static/"))
                .map(ipAndUrlOnly)
                .map(correctUrl)
                .map(it -> String.join(" visited ", it))
                .toArray(String[]::new);

        System.out.println("result = " + Arrays.toString(result));

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Now with transducers


        ITransducer<String, String> filterGets = filter(new Predicate<String>() {
            @Override
            public boolean test(String line) {
                return line.contains("GET /");
            }
        });

        ITransducer<String, String> filterStatics = remove(new Predicate<String>() {
            @Override
            public boolean test(String line) {
                return line.contains("GET /static/");
            }
        });

        ITransducer<String[], String> keepOnlyWantedParts = map(new com.cognitect.transducers.Function<String, String[]>() {
            @Override
            public String[] apply(String i) {
                return ipAndUrlOnly.apply(i);
            }
        });

        ITransducer<String[], String[]> makeFullUrl = map(new com.cognitect.transducers.Function<String[], String[]>() {
            @Override
            public String[] apply(String [] i) {
                return correctUrl.apply(i);
            }
        });

        ITransducer<String, String[]> reStringify = map(new com.cognitect.transducers.Function<String[], String>() {
            @Override
            public String apply(String[] i) {
                return String.join(" visited ", i);
            }
        });

        ITransducer<String, String> fullTransducer = compose(filterGets, compose(filterStatics, compose(keepOnlyWantedParts, compose(makeFullUrl, reStringify))));

        IStepFunction<ArrayList<String>, String> collectResults = new IStepFunction<ArrayList<String>, String>() {
            @Override
            public ArrayList<String> apply(ArrayList<String> result, String input, AtomicBoolean reduced) {
                result.add(input);
                return result;
            }
        };

        ArrayList<String> initialValue = new ArrayList<String>();
        ArrayList<String> linesList    = new ArrayList<String>(Arrays.asList(lines));


        ArrayList<String> transResult = transduce(fullTransducer, collectResults, initialValue, linesList);

        System.out.println("result = " + transResult.toString());
    }
}
