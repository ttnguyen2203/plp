import React from 'react';
import './css/Translate.css';
import Output from './Output';

class Translate extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      value: 'Type to translate to Pig Latin.',
      response: 'Ypetay otay anslatetray otay igpay atinlay.'
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.setState({value: event.target.value});
  }

  handleSubmit(event) {
    event.preventDefault();
    this.setState({
      response: "translation"
    }, function () {
      console.log("___submit", this.state.response)
    });

        // fetch("http://localhost:8080/translate")
    // .then(res => res.json())
    // .then(
    //   (result) => {
    //     this.setState({
    //       response: "translation"
    //     });
    //   },
    //   // Note: it's important to handle errors here
    //   // instead of a catch() block so that we don't swallow
    //   // exceptions from actual bugs in components.
    //   (error) => {
    //     this.setState({
    //       isLoaded: true,
    //       error
    //     });
    //   }
    // )
  }
    
      render() {
        return (
        <div className="Translate">
            <div className="Input">
              <form onSubmit={this.handleSubmit} >
                  <label>
                  <textarea value={this.state.value} onChange={this.handleChange} />
                  </label>
                  <input type="submit" value="Submit" />
              </form>
            </div>
            <Output response={this.state.response}/>
        </div>
        );
      }
  }
  
  export default Translate;