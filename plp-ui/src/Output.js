import React from 'react';
import './css/Output.css';

class Output extends React.Component {
    constructor(props) {
        super(props);
      }
    
      render() {
        const response = this.props.response;
        return (
        <div className="Output">
            <div className="Response">  
              <div className="ResponseOutput">
                {response}
              </div>
            </div>
        </div>
        );
      }
  }
  
  export default Output;