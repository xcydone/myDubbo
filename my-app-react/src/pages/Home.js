import React, {Component} from 'react';
import { Link } from 'react-router-dom';

export default class Home extends Component{
    render() {
        return (<div>
            <div>This is Home!</div>
            <div>
                <Link to="/profile" style={{color:'black'}}>
                    <div>点击跳转到Profile</div>
                </Link>
                <Link to="/user" style={{color:'black'}}>
                    <div>点击跳转到User</div>
                </Link>
            </div>
        </div>
        );
    }
}