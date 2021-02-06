import axios from 'axios'

class ProjectService {

    retrieveAllProjects(name) {
        //console.log('executed service')
        return axios.get(""
            //{ headers: { authorization: 'Basic ' + window.btoa(INSTRUCTOR + ":" + PASSWORD) } }
        );
    }
}

export default new ProjectService();